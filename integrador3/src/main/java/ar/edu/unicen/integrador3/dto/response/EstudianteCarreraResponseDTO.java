package ar.edu.unicen.integrador3.dto.response;

public record EstudianteCarreraResponseDTO(
        Long id,
        String nombreDeEstudiante,
        String nombreDeCarrera,
        int inscripcion,
        int graduacion,
        int atiguedad
) {}
