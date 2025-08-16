package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.VideoJuego;
import ar.edu.utn.frc.tup.piii.Repository.videojuegoRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.videoJuegoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class videojuegoServiceTest {

    @InjectMocks
    private videojuegoService videojuegoService;

    @Mock
    private videojuegoRepository videojuegoRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void listarVideojuego() {
        List<VideoJuego> juegos = List.of(new VideoJuego(), new VideoJuego());
        List<videoJuegoDTO> juegosDTO = List.of(new videoJuegoDTO(), new videoJuegoDTO());

        when(videojuegoRepository.findAll()).thenReturn(juegos);
        when(modelMapper.map(juegos, new TypeToken<List<videoJuegoDTO>>() {}.getType())).thenReturn(juegosDTO);

        List<videoJuegoDTO> result = videojuegoService.listarVideojuego();

        assertEquals(2, result.size());
        verify(videojuegoRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(juegos, new TypeToken<List<videoJuegoDTO>>() {}.getType());
    }

    @Test
    void getJuegoById() {
        Long id = 1L;
        Optional<VideoJuego> juego = Optional.of(new VideoJuego());
        Optional<videoJuegoDTO> juegoDTO = Optional.of(new videoJuegoDTO());

        when(videojuegoRepository.findById(id)).thenReturn(juego);
        when(modelMapper.map(juego, new TypeToken<Optional<videoJuegoDTO>>() {}.getType())).thenReturn(juegoDTO);

        Optional<videoJuegoDTO> result = videojuegoService.getJuegoById(id);

        assertTrue(result.isPresent());
        verify(videojuegoRepository).findById(id);
        verify(modelMapper).map(juego, new TypeToken<Optional<videoJuegoDTO>>() {}.getType());
    }
}