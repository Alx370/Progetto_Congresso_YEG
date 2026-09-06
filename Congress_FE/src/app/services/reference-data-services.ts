import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReferenceValue } from '../model/ReferenceValue';

@Injectable({ providedIn: 'root' })
export class ReferenceDataServices {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/reference-data';

  findRegions(): Observable<ReferenceValue[]> {
    return this.http.get<ReferenceValue[]>(`${this.baseUrl}/regions`);
  }

  findStakeholderTypes(): Observable<ReferenceValue[]> {
    return this.http.get<ReferenceValue[]>(`${this.baseUrl}/stakeholder-types`);
  }

  findEngagementChannels(): Observable<ReferenceValue[]> {
    return this.http.get<ReferenceValue[]>(`${this.baseUrl}/engagement-channels`);
  }
}
