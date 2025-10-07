package ar.edu.unicen.repository;

import ar.edu.unicen.dto.CarreraInscripcionDTO;
import ar.edu.unicen.dto.EstudianteResidenciaDTO;
import ar.edu.unicen.dto.Reporte;
import ar.edu.unicen.entity.Carrera;
import ar.edu.unicen.entity.Estudiante;
import ar.edu.unicen.entity.EstudianteCarrera;

import java.util.List;

public interface EstudianteCarreraRepository {
    void create(int id, int id_estudiante, int id_carrera, int inscripcion , int antiguedad, int graduacion);
    void update(EstudianteCarrera estudianteCarrera);
    void delete(int id);
    List<EstudianteResidenciaDTO> getAllEstudiantesCarreraByResidencia(int id_carrera, String residencia);
    List<CarreraInscripcionDTO> getEstudiantesInscriptos();
}
