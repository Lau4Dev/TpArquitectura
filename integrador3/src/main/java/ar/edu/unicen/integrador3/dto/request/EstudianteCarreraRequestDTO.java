package ar.edu.unicen.integrador3.dto.request;

public record EstudianteCarreraRequestDTO(
        Long id,
        Long dni,
        Long idCarrera,
        int inscripcion,
        int graduacion,
        int antiguedad
) {
}
