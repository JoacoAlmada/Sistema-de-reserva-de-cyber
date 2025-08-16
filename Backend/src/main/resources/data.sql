-- Clientes
INSERT INTO clientes (id, nombre_completo, email) VALUES
                                                      (1, 'Juan Pérez', 'juan@example.com'),
                                                      (2, 'María Gómez', 'maria@example.com'),
                                                      (3, 'Carlos López', 'carlos@example.com'),
                                                      (4, 'Ana García', 'ana@example.com');

-- Videojuegos
INSERT INTO videojuegos (id, titulo, genero) VALUES
                                                 (1, 'FIFA 23', 'Deportes'),
                                                 (2, 'Minecraft', 'Aventura'),
                                                 (3, 'GTA V', 'Acción'),
                                                 (4, 'Mario Kart', 'Carreras');

-- Puestos de juego
INSERT INTO puesto_juego (id, nombre, tipo) VALUES
                                                (1, 'PC Gamer 1', 'PC'),
                                                (2, 'Consola 1', 'Consola'),
                                                (3, 'PC Gamer 2', 'PC'),
                                                (4, 'Consola 2', 'Consola');

-- Reservas válidas (cumplen reglas de negocio)
INSERT INTO reserva (id, cliente_id, videojuego_id, puesto_id, fecha_hora, duracion_minutos, observaciones) VALUES
                                                                                                                (100, 1, 1, 1, '2025-07-01T12:00:00', 60, 'Con joystick'),
                                                                                                                (101, 2, 2, 2, '2025-07-01T14:00:00', 90, 'Traer auriculares'),
                                                                                                                (102, 1, 3, 3, '2025-07-02T11:00:00', 30, 'Sin preferencias'),
                                                                                                                (103, 3, 4, 1, '2025-07-01T21:00:00', 60, 'Con volante');

-- ❌ Reservas inválidas (para probar validaciones)

-- ⏰ fuera de horario (9:00)
INSERT INTO reserva (id, cliente_id, videojuego_id, puesto_id, fecha_hora, duracion_minutos, observaciones) VALUES
    (104, 4, 1, 2, '2025-07-01T09:00:00', 60, 'Horario inválido');

-- ❌ duración no permitida (45)
INSERT INTO reserva (id, cliente_id, videojuego_id, puesto_id, fecha_hora, duracion_minutos, observaciones) VALUES
    (105, 4, 2, 2, '2025-07-01T15:00:00', 45, 'Duración inválida');

-- ❌ solapamiento en puesto (puesto 1 ya ocupado 12:00 a 13:00)
INSERT INTO reserva (id, cliente_id, videojuego_id, puesto_id, fecha_hora, duracion_minutos, observaciones) VALUES
    (106, 2, 1, 1, '2025-07-01T12:30:00', 60, 'Solapamiento');

-- ❌ más de una reserva por cliente por día (cliente 1 ya tiene una el 2025-07-01)
INSERT INTO reserva (id, cliente_id, videojuego_id, puesto_id, fecha_hora, duracion_minutos, observaciones) VALUES
    (107, 1, 4, 4, '2025-07-01T18:00:00', 60, 'Duplicado cliente día');