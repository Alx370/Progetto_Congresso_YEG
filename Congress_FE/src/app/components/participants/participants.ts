import { Component, inject, OnInit, signal } from '@angular/core';
import { Participant } from '../../model/Participant';
import { ParticipantsServices } from '../../services/participants-services';
import { TableColumn, ViewAll } from '../view-all/view-all';

@Component({
  selector: 'app-participants',
  imports: [ViewAll],
  templateUrl: './participants.html',
  styleUrl: './participants.css',
})
export class Participants implements OnInit {
  private readonly participantsServices = inject(ParticipantsServices);

  readonly columns: TableColumn[] = [
    { key: 'excelId', label: 'ID Excel' },
    { key: 'fullName', label: 'Nome completo' },
    { key: 'email', label: 'E-mail' },
    { key: 'stakeholderType.name', label: 'Tipologia' },
    { key: 'region.name', label: 'Regione' },
    { key: 'engagementChannel.name', label: 'Canale' },
    { key: 'inDemDatabase', label: 'Nel database DEM', type: 'boolean' },
  ];

  readonly participants = signal<Participant[]>([]);
  readonly currentPage = signal(0);
  readonly pageSize = 25;
  readonly totalPages = signal(0);
  readonly totalElements = signal(0);
  readonly loading = signal(true);
  readonly errorMessage = signal('');

  ngOnInit(): void {
    this.loadParticipants();
  }

  previousPage(): void {
    if (this.currentPage() > 0) {
      this.loadParticipants(this.currentPage() - 1);
    }
  }

  nextPage(): void {
    if (this.currentPage() + 1 < this.totalPages()) {
      this.loadParticipants(this.currentPage() + 1);
    }
  }

  private loadParticipants(page = 0): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.participantsServices.findAll(page, this.pageSize).subscribe({
      next: (response) => {
        this.participants.set(response.content);
        this.currentPage.set(response.number);
        this.totalPages.set(response.totalPages);
        this.totalElements.set(response.totalElements);
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set(
          'Impossibile caricare i partecipanti. Verifica che il backend sia avviato sulla porta 8080.',
        );
        this.loading.set(false);
      },
    });
  }
}
