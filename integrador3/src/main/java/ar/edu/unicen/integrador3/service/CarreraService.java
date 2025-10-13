package ar.edu.unicen.integrador3.service;

import ar.edu.unicen.integrador3.dto.response.CarreraInscripcionDTO;
import ar.edu.unicen.integrador3.dto.response.CarreraResponseDTO;
import ar.edu.unicen.integrador3.entity.Carrera;
import ar.edu.unicen.integrador3.repository.CarreraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarreraService {
    private final CarreraRepository carreraRepository;

public CarreraResponseDTO create(CarreraResponseDTO request) {
    Carrera carrera = new Carrera();
    carrera.setId(request.id());
    carrera.setNombre(request.nombre());
    carrera.setDuracion(request.duracion());
    carreraRepository.save(carrera);
    return new CarreraResponseDTO(carrera.getId(), carrera.getNombre(), carrera.getDuracion());
}

public CarreraResponseDTO update(CarreraResponseDTO request) {
    Carrera carrera = carreraRepository.findById(request.id())
            .orElseThrow(() -> new EntityNotFoundException("No se encontro la carrera con el id: " + request.id()));
    carrera.setNombre(request.nombre());
    carrera.setDuracion(request.duracion());
    carreraRepository.save(carrera);
    return new CarreraResponseDTO(carrera.getId(), carrera.getNombre(), carrera.getDuracion());

}
}
