import {Component, inject, OnInit} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {ReservaDTO} from '../../models/reserva';
import {ReservaComponent} from '../reserva/reserva.component';
import {FilterComponent, Filtros} from '../filter/filter.component';

@Component({
  selector: 'app-home',
  imports: [
    ReservaComponent,
    FilterComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  reserva: ReservaDTO[] = [];
  private apiService = inject(ApiService);

  ngOnInit() {
    this.apiService.getReserva().subscribe({
      next: (response) => {
        this.reserva = response;
      },
      error: (error) => {
        console.error('Error al obtener reservas:', error);
      }
    });
  }

  aplicarFiltros(filtros: Filtros) {
    this.apiService.getReserva(filtros.cliente_id , filtros.puesto_id , filtros.videojuego_id , filtros.fechaHora).subscribe(response => {
      this.reserva = response;
    })
  }
}

