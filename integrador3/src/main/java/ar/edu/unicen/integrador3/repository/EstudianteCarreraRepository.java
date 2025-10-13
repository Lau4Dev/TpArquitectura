package ar.edu.unicen.integrador3.repository;

import ar.edu.unicen.integrador3.dto.response.CarreraInscripcionDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteResidenciaDTO;
import ar.edu.unicen.integrador3.dto.response.ReporteDTO;
import ar.edu.unicen.integrador3.entity.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long> {

    @Query("SELECT " +
             "new ar.edu.unicen.integrador3.dto.response.EstudianteResidenciaDTO(e.nombre, c.nombre, e.ciudad) " +
             "FROM EstudianteCarrera ec " +
             "JOIN ec.carrera c " +
             "JOIN ec.estudiante e " +
             "WHERE c.id = :id_carrera AND e.ciudad = :residencia")
    List<EstudianteResidenciaDTO> getAllEstudiantesCarreraByResidencia(Long id_carrera, String residencia);

    @Query("SELECT new ar.edu.unicen.integrador3.dto.response.CarreraInscripcionDTO(c.id,c.nombre,COUNT(ec)) " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c "+
            "GROUP BY ec.carrera " +
            "ORDER BY COUNT(ec) DESC")
    List<CarreraInscripcionDTO> getEstudiantesInscriptos();

     @Query("SELECT new ar.edu.unicen.integrador3.dto.response.ReporteDTO(" +
             "c.nombre, " +
             "ec.inscripcion, " +
             "COUNT(ec), " +
             "SUM(CASE WHEN ec.graduacion > 0 THEN 1 ELSE 0 END)) " +
             "FROM EstudianteCarrera ec " +
             "JOIN ec.carrera c " +
             "GROUP BY c.nombre, ec.inscripcion " +
             "ORDER BY c.nombre ASC, ec.inscripcion ASC")
     List<ReporteDTO> getReportes();

}

