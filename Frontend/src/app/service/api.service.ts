import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ClienteDTO} from '../models/cliente';
import {VideoJuegoDTO} from '../models/video-juego';
import {PuestoJuegoDTO} from '../models/puesto-juego';
import {ReservaDTO, ReservaRequestDTO} from '../models/reserva';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }

  getCliente() {
    return this.http.get<ClienteDTO[]>(`${this.apiURL}/clientes`)
  }

  getJuego() {
    return this.http.get<VideoJuegoDTO[]>(`${this.apiURL}/videojuegos`)
  }

  getPuestoJuego() {
    return this.http.get<PuestoJuegoDTO[]>(`${this.apiURL}/puestos`)
  }

  getReserva(clienteId?:number , videojuegoId?:number , puestoid?:number , fechaHora?: string ) {
    let  params = new HttpParams()
    if (clienteId) params = params.set('cliente_id', clienteId);
    if (videojuegoId) params  = params.set('videojuego_id', videojuegoId);
    if (puestoid) params  = params.set('puesto_id', puestoid);
    if (fechaHora) params  = params.set('fechaHora', fechaHora);


    return this.http.get<ReservaDTO[]>(`${this.apiURL}/reservas`, {params});
  }

  postReserva(reserva: ReservaRequestDTO) {
    return this.http.post<ReservaRequestDTO[]>(`${this.apiURL}/reservas`, reserva);
  }


}
