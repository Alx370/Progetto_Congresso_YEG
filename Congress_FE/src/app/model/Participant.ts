import { ReferenceValue } from './ReferenceValue';

export interface Participant {
  id: number;
  excelId: number;
  fullName: string;
  email: string;
  stakeholderType: ReferenceValue;
  region: ReferenceValue;
  engagementChannel: ReferenceValue;
  inDemDatabase: boolean;
  createdAt: string;
  updatedAt: string;
}
