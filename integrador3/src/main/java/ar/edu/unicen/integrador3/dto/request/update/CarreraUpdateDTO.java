package ar.edu.unicen.integrador3.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CarreraUpdateDTO(
        @NotBlank String nombre,
        @PositiveOrZero int duracion
) {}
