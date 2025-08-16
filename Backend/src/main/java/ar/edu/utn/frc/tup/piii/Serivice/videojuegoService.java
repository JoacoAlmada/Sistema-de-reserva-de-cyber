package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.VideoJuego;
import ar.edu.utn.frc.tup.piii.Repository.videojuegoRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.videoJuegoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class videojuegoService {
    @Autowired
    private videojuegoRepository VideojuegoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<videoJuegoDTO> listarVideojuego() {
        List<VideoJuego> medico = VideojuegoRepository.findAll();
        return modelMapper.map(medico, new TypeToken<List<videoJuegoDTO>>() {}.getType());
    }

    public Optional<videoJuegoDTO> getJuegoById(Long id) {
        return modelMapper.map(VideojuegoRepository.findById(id) ,new TypeToken<Optional<videoJuegoDTO>>() {}.getType());
    }
}
