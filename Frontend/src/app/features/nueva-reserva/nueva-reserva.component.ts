import {Component, inject, OnInit} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {ClienteDTO} from '../../models/cliente';
import {PuestoJuegoDTO} from '../../models/puesto-juego';
import {VideoJuegoDTO} from '../../models/video-juego';
import {FormsModule, NgForm} from '@angular/forms';

@Component({
  selector: 'app-nueva-reserva',
  imports: [
    FormsModule
  ],
  templateUrl: './nueva-reserva.component.html',
  styleUrl: './nueva-reserva.component.css'
})
export class NuevaReservaComponent implements OnInit {

  clientes : ClienteDTO[] = [];
  puestos : PuestoJuegoDTO[] = [];
  juegos : VideoJuegoDTO[] = [];

  private apiService = inject(ApiService);

  reserva: any = {
    cliente_id: 0,
    puesto_id: 0,
    videojuego_id: 0,
    fechaHora: "",
    duracionMinutos: 0,
    observaciones: ""
  };

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

  crearReserva(form: NgForm) {
    if (form.invalid) {
      alert("Faltan algunos campos")
      return;
    }
    if (this.reserva.fechaHora && this.reserva.fechaHora.length === 16) {
      this.reserva.fechaHora += ':00';
    }
    this.apiService.postReserva(this.reserva).subscribe(response => {
      alert("Turno creado con exito");
    })
  }
}
