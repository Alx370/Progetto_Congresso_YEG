import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'participants',
    title: 'Congresso',
    loadComponent: () =>
      import('./components/participants/participants').then(
        (component) => component.Participants,
      ),
  },
  { path: '', pathMatch: 'full', redirectTo: 'participants' }
];
