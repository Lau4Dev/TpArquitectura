package ar.edu.unicen.integrador3.service;

import ar.edu.unicen.integrador3.dto.request.EstudianteCarreraRequestDTO;
import ar.edu.unicen.integrador3.dto.response.CarreraInscripcionDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteCarreraResponseDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteResidenciaDTO;
import ar.edu.unicen.integrador3.dto.response.ReporteDTO;
import ar.edu.unicen.integrador3.entity.Carrera;
import ar.edu.unicen.integrador3.entity.Estudiante;
import ar.edu.unicen.integrador3.entity.EstudianteCarrera;
import ar.edu.unicen.integrador3.repository.CarreraRepository;
import ar.edu.unicen.integrador3.repository.EstudianteCarreraRepository;
import ar.edu.unicen.integrador3.repository.EstudianteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteCarreraService {
    private final EstudianteCarreraRepository estudianteCarreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    @Transactional
    public EstudianteCarreraResponseDTO create(EstudianteCarreraRequestDTO request) {
        Estudiante e = estudianteRepository.findById(request.dni()).orElseThrow(
                ()-> new EntityNotFoundException("Estudiante no encontrado con el dni " +  request.dni())
        );

        Carrera c = carreraRepository.findById(request.idCarrera()).orElseThrow(
                ()-> new EntityNotFoundException("Carrera no encontrada " +  request.idCarrera())
        );

        EstudianteCarrera ec = new EstudianteCarrera();
        ec.setId(request.id());
        ec.setEstudiante(e);
        ec.setCarrera(c);
        ec.setInscripcion(request.inscripcion());
        ec.setGraduacion(request.graduacion());
        ec.setAntiguedad(request.antiguedad());

        estudianteCarreraRepository.save(ec);

        return new EstudianteCarreraResponseDTO(
                ec.getId(),
                ec.getEstudiante().getNombre(),
                ec.getCarrera().getNombre(),
                ec.getInscripcion(),
                ec.getGraduacion(),
                ec.getAntiguedad()
        );
    }

    @Transactional
    public EstudianteCarreraResponseDTO updated(EstudianteCarreraRequestDTO request) {

        EstudianteCarrera ec = estudianteCarreraRepository.findById(request.id()).orElseThrow(
                ()-> new EntityNotFoundException("EstudianteCarrera no encontrado con el id " +  request.id())
        );
        Estudiante e = estudianteRepository.findById(request.dni()).orElseThrow(
                ()-> new EntityNotFoundException("Estudiante no encontrado con el dni " +  request.dni())
        );

        Carrera c = carreraRepository.findById(request.idCarrera()).orElseThrow(
                ()-> new EntityNotFoundException("Carrera no encontrada con el id " +  request.idCarrera())
        );

        ec.setEstudiante(e);
        ec.setCarrera(c);
        ec.setInscripcion(request.inscripcion());
        ec.setGraduacion(request.graduacion());
        ec.setAntiguedad(request.antiguedad());

        estudianteCarreraRepository.save(ec);

        return new EstudianteCarreraResponseDTO(
                ec.getId(),
                ec.getEstudiante().getNombre(),
                ec.getCarrera().getNombre(),
                ec.getInscripcion(),
                ec.getGraduacion(),
                ec.getAntiguedad()
        );
    }

    public void deleteById(Long id) {
        EstudianteCarrera ec = estudianteCarreraRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("EstudianteCarrera no encontrado con el id " +  id)
        );
        estudianteCarreraRepository.delete(ec);
    }


    public List<EstudianteResidenciaDTO> getAllEstudiantesCarreraByResidencia(Long idCarrera, String residencia) {
        Carrera c =  carreraRepository.findById(idCarrera).orElseThrow(
                ()-> new EntityNotFoundException("no se encontro la carrera con el id " +  idCarrera)
        );
        return estudianteCarreraRepository.findAllEstudiantesCarreraByResidencia(idCarrera, residencia);
    }

    public List<CarreraInscripcionDTO> findEstudiantesInscriptos() {
        return estudianteCarreraRepository.findEstudiantesInscriptos();
    }

    public List<ReporteDTO> findReportes() {
        return estudianteCarreraRepository.findReportes();
    }


}
