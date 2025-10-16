package ar.edu.unicen.integrador3.service;

import ar.edu.unicen.integrador3.dto.request.CarreraRequestDTO;
import ar.edu.unicen.integrador3.dto.request.update.CarreraUpdateDTO;
import ar.edu.unicen.integrador3.dto.response.CarreraInscripcionDTO;
import ar.edu.unicen.integrador3.dto.response.CarreraResponseDTO;
import ar.edu.unicen.integrador3.entity.Carrera;
import ar.edu.unicen.integrador3.repository.CarreraRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarreraService {
    private final CarreraRepository carreraRepository;


@Transactional
public CarreraResponseDTO save(CarreraRequestDTO request) {
    Carrera carrera = new Carrera();
    carrera.setId(request.id());
    carrera.setNombre(request.nombre());
    carrera.setDuracion(request.duracion());
    carreraRepository.save(carrera);
    return new CarreraResponseDTO(carrera.getId(), carrera.getNombre(), carrera.getDuracion());
}

@Transactional
public CarreraResponseDTO update( Long id, CarreraUpdateDTO request) {
    Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se encontro la carrera con el id: " + id));
    carrera.setNombre(request.nombre());
    carrera.setDuracion(request.duracion());
    carreraRepository.save(carrera);
    return new CarreraResponseDTO(carrera.getId(), carrera.getNombre(), carrera.getDuracion());

}
public void delete(Long id) {
    Carrera carrera = carreraRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontro la carrera con el id: " + id));
    carreraRepository.delete(carrera);
}
}
