import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageResponse } from '../model/PageResponse';
import { Participant } from '../model/Participant';
import { ParticipantInteractions } from '../model/ParticipantInteractions';

@Injectable({ providedIn: 'root' })
export class ParticipantsServices {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/participants';

  findAll(page = 0, size = 25): Observable<PageResponse<Participant>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', 'id,asc');

    return this.http.get<PageResponse<Participant>>(this.baseUrl, { params });
  }

  findById(id: number): Observable<Participant> {
    return this.http.get<Participant>(`${this.baseUrl}/${id}`);
  }

  findByEmail(email: string): Observable<Participant> {
    const params = new HttpParams().set('email', email);
    return this.http.get<Participant>(`${this.baseUrl}/by-email`, { params });
  }

  findInteractions(participantId: number): Observable<ParticipantInteractions> {
    return this.http.get<ParticipantInteractions>(
      `${this.baseUrl}/${participantId}/interactions`,
    );
  }
}
