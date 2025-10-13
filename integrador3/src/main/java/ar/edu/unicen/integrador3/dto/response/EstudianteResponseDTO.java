package ar.edu.unicen.integrador3.dto.response;

public record EstudianteResponseDTO(
         Long dni,
         String nombreYApellido,
         String genero,
         String ciudad,
         int numeroLibreta
) {}
