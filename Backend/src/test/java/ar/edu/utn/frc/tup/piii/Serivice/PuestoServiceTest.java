package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.PuestoJuego;
import ar.edu.utn.frc.tup.piii.Repository.puestoRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.PuestoDTO;
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
class PuestoServiceTest {

    @InjectMocks
    private PuestoService puestoService;

    @Mock
    private puestoRepository puestoRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void listarPuestos() {
        List<PuestoJuego> puestos = List.of(new PuestoJuego(), new PuestoJuego());
        List<PuestoDTO> puestosDTO = List.of(new PuestoDTO(), new PuestoDTO());

        when(puestoRepository.findAll()).thenReturn(puestos);
        when(modelMapper.map(puestos, new TypeToken<List<PuestoDTO>>() {}.getType())).thenReturn(puestosDTO);

        List<PuestoDTO> result = puestoService.listarPuestos();

        assertEquals(2, result.size());
        verify(puestoRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(puestos, new TypeToken<List<PuestoDTO>>() {}.getType());
    }

    @Test
    void getPuestoById() {
        Long id = 1L;
        Optional<PuestoJuego> puesto = Optional.of(new PuestoJuego());
        Optional<PuestoDTO> puestoDTO = Optional.of(new PuestoDTO());

        when(puestoRepository.findById(id)).thenReturn(puesto);
        when(modelMapper.map(puesto, new TypeToken<Optional<PuestoDTO>>() {}.getType())).thenReturn(puestoDTO);

        Optional<PuestoDTO> result = puestoService.getPuestoById(id);

        assertTrue(result.isPresent());
        verify(puestoRepository).findById(id);
    }
}