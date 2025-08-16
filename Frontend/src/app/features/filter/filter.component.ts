import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {ClienteDTO} from '../../models/cliente';
import {PuestoJuegoDTO} from '../../models/puesto-juego';
import {VideoJuegoDTO} from '../../models/video-juego';
import {ApiService} from '../../service/api.service';
import {FormsModule} from '@angular/forms';


export interface Filtros {
  cliente_id?: number,
  videojuego_id?: number,
  puesto_id?: number,
  fechaHora?: string,
}

@Component({
  selector: 'app-filter',
  imports: [
    FormsModule
  ],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent  implements OnInit {
  @Output() filtrosAplicados = new EventEmitter<Filtros>();

  clientes : ClienteDTO[] = [];
  puestos : PuestoJuegoDTO[] = [];
  juegos : VideoJuegoDTO[] = [];

  private apiService = inject(ApiService);

  filtros: Filtros = {}

  ngOnInit() {
    this.apiService.getJuego().subscribe(response => {
      this.juegos = response;
    })

    this.apiService.getCliente().subscribe(response => {
      this.clientes = response;
    })

    this.apiService.getPuestoJuego().subscribe(response => {
      this.puestos = response;
    })
  }

  aplicarFiltros() {
    this.filtrosAplicados.emit(this.filtros);
  }
}
