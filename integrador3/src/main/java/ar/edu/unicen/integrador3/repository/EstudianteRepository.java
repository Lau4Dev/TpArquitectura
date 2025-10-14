package ar.edu.unicen.integrador3.repository;

import ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO;
import ar.edu.unicen.integrador3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    @Query("SELECT new ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO(e.dni,CONCAT(e.nombre, ' ' , e.apellido), e.genero, e.ciudad, e.numeroLibreta ) " +
            "FROM Estudiante e " +
            "ORDER BY e.nombre")
    List<EstudianteResponseDTO> findAllOrderByName();

    @Query("SELECT new ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO(e.dni,CONCAT(e.nombre, e.apellido), e.genero, e.ciudad, e.numeroLibreta ) " +
            "FROM Estudiante e WHERE e.numeroLibreta = :libreta")
    EstudianteResponseDTO findByLibreta(int libreta);

    @Query( "SELECT new ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO(e.dni,CONCAT(e.nombre, e.apellido)," +
            " e.genero, e.ciudad, e.numeroLibreta)" +
            " FROM Estudiante e WHERE e.genero = :genero")
    List<EstudianteResponseDTO> findAllEstudianteByGenero(String genero);
}
