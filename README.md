# Progetto Congresso YEG

## Da dove sono partito

Questo è il secondo progetto full stack che realizzo. Ho quindi cercato di costruire una soluzione che riuscissi non soltanto a far funzionare, ma anche a comprendere e spiegare. Durante lo sviluppo alcune parti, soprattutto l'analisi aggregata richiesta per la dashboard e infine la Dockerizzazione, si sono rivelate più dispendiose di tempo quanto avessi previsto. Ho preferito documentare chiaramente il risultato raggiunto e i suoi limiti.

Il flusso che ho realizzato è:

```text
Excel -> conversione Python -> CSV -> importazione Spring Boot -> H2 -> API REST -> Angular
```

## Tecnologie utilizzate

- **Java 21 e Spring Boot 4** per il backend;
- **Spring Data JPA e Hibernate** per l'accesso ai dati;
- **JdbcTemplate** per mantenere esplicita la logica di importazione e confronto dei CSV;
- **H2** come database relazionale in memoria;
- **Flyway** per creare lo schema in modo ripetibile;
- **ModelMapper** per convertire le entità nelle classi esposte dalle API;
- **Python e openpyxl** per leggere e trasformare il file Excel;
- **Angular 22** per il frontend;
- **Maven e npm** per dipendenze, build e avvio dei due progetti;

Non ho inserito Docker tra le tecnologie utilizzate perché, nello stato attuale, il progetto non contiene ancora Dockerfile o Docker Compose.

## Ragionamento sul database

Ho scelto un database relazionale perché le informazioni hanno legami chiari. Come database ho usato H2 in memoria per una questione di semplicitá e velocitá. Infatti Mi ha permesso di avviare il progetto senza installare un server esterno e di ricreare sempre lo stesso schema tramite Flyway. In un'evoluzione del progetto sceglierei PostgreSQL, mantenendo lo stesso modello relazionale e adattando la configurazione e la migrazione.

### Perché non ho copiato il foglio in una sola tabella

Ho escluso questa soluzione perché 17 colonne che rappresentano touchpoint differenti avrebbero reso la tabella molto larga, ripetitiva e meno semplice da interrogare.

Ho separato le informazioni anagrafiche dalle interazioni:

- `partecipanti` contiene identificativo Excel, nome, e-mail e riferimenti anagrafici;
- `tipologie_stakeholder` contiene le tipologie senza ripeterne il testo per ogni partecipante;
- `regioni` contiene le regioni;
- `canali_ingaggio` contiene i canali di acquisizione;
- `interazioni_email` raccoglie invio, consegna e apertura della DEM;
- `interazioni_linkedin` raccoglie reach e interazioni di annuncio e recap;
- `interazioni_stand` raccoglie visita, giorno, visualizzazioni e scroll;
- `interazioni_sala_vip` raccoglie accesso e risposte alla word cloud;
- `interazioni_simposio` raccoglie presenza, permanenza, focus rate e quiz completati.

Le cinque tabelle delle interazioni hanno una relazione 1 a 1 con il partecipante. Questa scelta rispecchia il dataset, che contiene un solo riepilogo per persona e per gruppo di touchpoint.

## Script di importanzione

Lo script `Congress_BE/scripts/converti_excel_csv.py` legge:

- `01_Interazioni`, utilizzato come dizionario per controllare intestazioni, nomi tecnici e tipi dei 17 touchpoint;
- `02_Partecipanti`, contenente i dati da importare.

Il risultato sono due file:

- `Congress_BE/dati/partecipanti.csv`, con i dati anagrafici;
- `Congress_BE/dati/interazioni.csv`, con i touchpoint collegati tramite e-mail.

## Importazione ripetibile

Flyway crea lo schema usando `Congress_BE/src/main/resources/db/migration/V1__schema.sql`. Successivamente ImportRunner carica prima i partecipanti e poi le interazioni.

1. inserisce il partecipante se non esiste;
2. aggiorna i valori se sono cambiati;
3. non esegue aggiornamenti se i dati sono uguali.

Questo rende lo script ripetibile eseguirlo nuovamente sugli stessi file non crea duplicati.

## Struttura del backend

Per il backend ho seguito una struttura a livelli che conoscevo già e che mi ha aiutato a capire dove collocare ogni responsabilità:

```text
application/
├── api/controllers/       endpoint REST
├── business/
│   ├── internal/
│   │   ├── domains/       entità JPA
│   │   ├── repository/    repository Spring Data JPA
│   │   └── services/      implementazioni dei servizi
│   └── publishing/        interfacce dei servizi
├── configurations/        configurazioni condivise
└── views/item/            oggetti restituiti dalle API
```

Le interfacce in publishing separano ciò che un servizio offre dalla sua implementazione. Le entità del database non vengono restituite direttamente dai controller: ModelMapper le converte negli oggetti presenti in views/item.

Gli endpoint attualmente disponibili sono:

- `GET /api/participants`;
- `GET /api/participants/{id}`;
- `GET /api/participants/by-email?email=...`;
- `GET /api/participants/{id}/interactions`;
- `GET /api/reference-data/regions`;
- `GET /api/reference-data/stakeholder-types`;
- `GET /api/reference-data/engagement-channels`.

## Frontend realizzato

Il frontend è stato sviluppato con Angular. Ho creato i model TypeScript corrispondenti agli oggetti restituiti dal backend, i service basati su HttpClient e un componente generico dinamico chiamato view-all per mostrare i dati.

La pagina attualmente implementata mostra l'elenco paginato dei partecipanti con tipologia, regione, canale di ingaggio e presenza nel database DEM. Gestisce caricamento, errore e pagina senza risultati. Il frontend usa URL relativi come `/api/participants`; durante lo sviluppo `proxy.conf.json` inoltra queste richieste al backend sulla porta 8080.

La traccia richiedeva una dashboard con funnel, confronto per dimensione anagrafica, andamento giornaliero e un filtro globale. Avevo iniziato il frontend pensando a una visualizzazione più semplice di tutti i partecipanti e ho sottovalutato la parte di aggregazione necessaria per costruire le tre viste. Per completarla servirebbero nuove query aggregate nel backend e nuovi oggetti di risposta, prima ancora dei relativi componenti Angular.

## Stato attuale e limiti

Al momento ci sono queste funzioni nell'applicazione:

- conversione automatica dell'Excel in CSV;
- normalizzazione dei principali tipi di dato;
- schema relazionale creato con Flyway;
- importazione automatica e ripetibile;
- suddivisione dei 17 touchpoint in tabelle tematiche;
- API REST per partecipanti, dati di riferimento e interazioni;
- pagina Angular in sola lettura con paginazione;
- collegamento frontend-backend tramite proxy.

É un applicazione full stack funzionante, ma non copre l'intera consegna. Il passo successivo sarebbe modellare esplicitamente touchpoint e fasi, costruire endpoint analitici aggregati e infine aggiungere Docker Compose.

## Avvio del progetto

### 1. Conversione del file Excel

Il dataset `Dataset Evento Congresso 2025.xlsx` deve trovarsi nella cartella principale del progetto.

```powershell
cd Congress_BE
python -m pip install -r scripts/requirements.txt
python scripts/converti_excel_csv.py
```

La conversione può essere saltata se i due CSV sono già presenti in `Congress_BE/dati`.

### 2. Avvio del backend

Sono richiesti Java 21 e Maven.

```powershell
cd Congress_BE
mvn spring-boot:run
```

Il backend è disponibile su `http://localhost:8080/api`.

La console H2 è raggiungibile su `http://localhost:8080/api/h2-console` usando:

```text
JDBC URL: jdbc:h2:mem:congress
User Name: sa
Password: [vuota]
```

### 3. Avvio del frontend

In un secondo terminale:

```powershell
cd Congress_FE
npm install
npm start
```

La pagina dei partecipanti è disponibile su `http://localhost:4200/participants`.
