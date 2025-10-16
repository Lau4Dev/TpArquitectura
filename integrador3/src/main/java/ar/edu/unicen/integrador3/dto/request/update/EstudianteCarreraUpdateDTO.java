package ar.edu.unicen.integrador3.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EstudianteCarreraUpdateDTO(
        @NotNull Long dni,
        @NotNull Long idCarrera,
        @PositiveOrZero int inscripcion,
        @PositiveOrZero int graduacion,
        @PositiveOrZero int antiguedad
) {}
