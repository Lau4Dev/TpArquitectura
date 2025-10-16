package ar.edu.unicen.integrador3.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EstudianteRequestUpdateDTO(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @Min(0) int edad,
        @NotBlank String genero,
        @NotBlank String ciudad,
        @Min(0) int numeroLibreta
) {
}
