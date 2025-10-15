package ar.edu.unicen.integrador3.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EstudianteRequestDTO(
         Long dni,
         @NotBlank String nombre,
         @NotBlank String apellido,
         @Min(0) int edad,
         @NotBlank String genero,
         @NotBlank String ciudad,
         @Min(0) int numeroLibreta
) {}
