package ar.edu.unicen.repository;

import ar.edu.unicen.dto.EstudianteDTO;
import ar.edu.unicen.entity.Estudiante;

import java.util.List;

public interface EstudianteRepository {
    void create(Estudiante estudiante);
    void update(Estudiante estudiante);
    void delete(int id);
    List<EstudianteDTO> findAllOrderByName();
    List<EstudianteDTO> getAllEstudiantesByGenero(String genero);
    EstudianteDTO findByLibreta(int libreta);

}
