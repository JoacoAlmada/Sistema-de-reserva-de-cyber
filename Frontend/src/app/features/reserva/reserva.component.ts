import {Component, Input} from '@angular/core';
import {ReservaDTO} from '../../models/reserva';

@Component({
  selector: 'app-reserva',
  imports: [],
  templateUrl: './reserva.component.html',
  styleUrl: './reserva.component.css'
})
export class ReservaComponent {
@Input() reservas: ReservaDTO[] = [];
}
