import {ClienteDTO} from './cliente';
import {VideoJuegoDTO} from './video-juego';
import {PuestoJuegoDTO} from './puesto-juego';


export interface ReservaDTO {
  id: number,
  cliente: ClienteDTO,
  videojuego: VideoJuegoDTO,
  puesto: PuestoJuegoDTO,
  fechaHora: string,
  duracionMinutos: number,
  observaciones: string
}

export interface ReservaRequestDTO {
  clienteId: number,
  videojuegoId: number,
  puestoid: number,
  fechaHora: string,
  duracionMinutos: number,
  observaciones: string
}
