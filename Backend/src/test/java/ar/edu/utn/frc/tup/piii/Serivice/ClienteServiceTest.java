package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.Cliente;
import ar.edu.utn.frc.tup.piii.Repository.clienteRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.ClienteDTO;
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
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private clienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void listarClientes() {
        List<Cliente> clientes = List.of(new Cliente(), new Cliente());
        List<ClienteDTO> clientesDTO = List.of(new ClienteDTO(), new ClienteDTO());

        when(clienteRepository.findAll()).thenReturn(clientes);
        when(modelMapper.map(clientes, new TypeToken<List<ClienteDTO>>() {}.getType())).thenReturn(clientesDTO);

        List<ClienteDTO> result = clienteService.listarClientes();

        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(clientes, new TypeToken<List<ClienteDTO>>() {}.getType());
    }

    @Test
    void getClienteById() {
        Long id = 1L;
        Optional<Cliente> cliente = Optional.of(new Cliente());
        Optional<ClienteDTO> clienteDTO = Optional.of(new ClienteDTO());

        when(clienteRepository.findById(id)).thenReturn(cliente);
        when(modelMapper.map(cliente, new TypeToken<Optional<ClienteDTO>>() {}.getType())).thenReturn(clienteDTO);

        Optional<ClienteDTO> result = clienteService.getClienteById(id);

        assertTrue(result.isPresent());
        verify(clienteRepository).findById(id);
        verify(modelMapper).map(cliente, new TypeToken<Optional<ClienteDTO>>() {}.getType());
    }
}