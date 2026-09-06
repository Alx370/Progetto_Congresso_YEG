export interface EmailInteraction {
  demSent: boolean;
  demDelivered: boolean;
  demOpened: boolean;
}

export interface LinkedInInteraction {
  adReach: boolean;
  adInteraction: boolean;
  recapReach: boolean;
  recapInteraction: boolean;
}

export interface StandInteraction {
  standVisited: boolean;
  visitDate: string | null;
  views: number;
  scroll: number;
}

export interface VipRoomInteraction {
  vipRoomAccess: boolean;
  wordcloudAnswers: number;
}

export interface SymposiumInteraction {
  symposiumAttendance: boolean;
  durationMinutes: number | null;
  focusRate: number | null;
  completedQuizzes: number;
}

export interface ParticipantInteractions {
  participantId: number;
  email: EmailInteraction;
  linkedIn: LinkedInInteraction;
  stand: StandInteraction;
  vipRoom: VipRoomInteraction;
  symposium: SymposiumInteraction;
}
