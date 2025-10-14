package ar.edu.unicen.integrador3.service;

import ar.edu.unicen.integrador3.dto.request.EstudianteRequestDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO;
import ar.edu.unicen.integrador3.entity.Estudiante;
import ar.edu.unicen.integrador3.repository.EstudianteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    @Transactional
    public EstudianteResponseDTO create(EstudianteRequestDTO request){
        Estudiante estudiante = new Estudiante(
                request.dni(),
                request.nombre(),
                request.apellido(),
                request.edad(),
                request.genero(),
                request.ciudad(),
                request.numeroLibreta());

        estudianteRepository.save(estudiante);
        return new EstudianteResponseDTO(request.dni(),
                request.nombre() + " " + request.apellido(),
                                        request.genero(),
                                        request.ciudad(),
                                        request.numeroLibreta());
    }

    @Transactional
    public EstudianteResponseDTO update(EstudianteRequestDTO request) {
        Estudiante estudiante = estudianteRepository.findById(request.dni())
                .orElseThrow(() -> new RuntimeException("No se encontro el estudiante con el dni: " + request.dni()));

        estudiante.setNombre(request.nombre());
        estudiante.setApellido(request.apellido());
        estudiante.setEdad(request.edad());
        estudiante.setGenero(request.genero());
        estudiante.setCiudad(request.ciudad());
        estudiante.setNumeroLibreta(request.numeroLibreta());
        estudianteRepository.save(estudiante);
        return new EstudianteResponseDTO(request.dni(),
                request.nombre() + " " + request.apellido(),
                request.genero(),
                request.ciudad(),
                request.numeroLibreta());
    }

    public void delete(Long dni) {
        Estudiante estudiante = estudianteRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("No se encontro el estudiante con el dni: " + dni));
        estudianteRepository.delete(estudiante);
    }

    public EstudianteResponseDTO findByDNI(Long dni) {
        Estudiante estudiante = estudianteRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("No se encontro el estudiante con el dni: " + dni));
        return new EstudianteResponseDTO(estudiante.getDni(),
                estudiante.getNombre() + " " + estudiante.getApellido(),
                estudiante.getGenero(),
                estudiante.getCiudad(),
                estudiante.getNumeroLibreta());
    }

    public List<EstudianteResponseDTO> findAllOrderByName() {
        List<EstudianteResponseDTO> estudiantes = estudianteRepository.findAllOrderByName();
        if (estudiantes.isEmpty()) throw new RuntimeException("No se encontraron estudiantes");
        return estudiantes;
    }

    public EstudianteResponseDTO findByLibreta(int libreta) {
        EstudianteResponseDTO estudianteResponse = estudianteRepository.findByLibreta(libreta);
        if(estudianteResponse == null) throw new EntityNotFoundException("No se encontro el estudiante con la libreta: " + libreta);
        return estudianteResponse;
    }

    public List<EstudianteResponseDTO> findAllEstudianteByGenero(String genero) {
        List<EstudianteResponseDTO> estudiantesResponse = estudianteRepository.findAllEstudianteByGenero(genero);
        if(estudiantesResponse.isEmpty()) throw new EntityNotFoundException("No se encontraron estudiantes con el genero: " + genero);
        return estudiantesResponse;
    }
}
