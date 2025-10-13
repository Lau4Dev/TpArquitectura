package ar.edu.unicen.integrador3.dto.request;

public record EstudianteRequestDTO(
         Long dni,
         String nombre,
         String apellido,
         int edad,
         String genero,
         String ciudad,
         int numeroLibreta
) {}
