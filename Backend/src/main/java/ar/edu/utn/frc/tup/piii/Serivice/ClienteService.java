package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.Cliente;
import ar.edu.utn.frc.tup.piii.Repository.clienteRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.ClienteDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private clienteRepository ClienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = ClienteRepository.findAll();
        return modelMapper.map(clientes, new TypeToken<List<ClienteDTO>>() {}.getType());

    }

    public Optional<ClienteDTO> getClienteById(Long id) {
        return modelMapper.map(ClienteRepository.findById(id) ,new TypeToken<Optional<ClienteDTO>>() {}.getType());
    }

}
