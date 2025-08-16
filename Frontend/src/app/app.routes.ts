import { Routes } from '@angular/router';
import {ReservaComponent} from './features/reserva/reserva.component';
import {NuevaReservaComponent} from './features/nueva-reserva/nueva-reserva.component';
import {HomeComponent} from './features/home/home.component';

export const routes: Routes = [
  {path: '', redirectTo: 'reservas', pathMatch: 'full'},
  { path: 'reservas', component: HomeComponent  },
  { path: 'alta-reserva', component: NuevaReservaComponent}
];
