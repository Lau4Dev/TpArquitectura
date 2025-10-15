package ar.edu.unicen.integrador3.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CarreraRequestDTO(
        Long id,
        @NotBlank String nombre,
        int duracion
) {}
