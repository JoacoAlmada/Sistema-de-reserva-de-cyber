package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.PuestoJuego;
import ar.edu.utn.frc.tup.piii.Repository.puestoRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.PuestoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuestoService {
    @Autowired
    private puestoRepository PuestoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<PuestoDTO> listarPuestos() {
        List<PuestoJuego> medico = PuestoRepository.findAll();
        return modelMapper.map(medico, new TypeToken<List<PuestoDTO>>() {}.getType());
    }

    public Optional<PuestoDTO> getPuestoById(Long id) {
        return modelMapper.map(PuestoRepository.findById(id) ,new TypeToken<Optional<PuestoDTO>>() {}.getType());
    }
}
