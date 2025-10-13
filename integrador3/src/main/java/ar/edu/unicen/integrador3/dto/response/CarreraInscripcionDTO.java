package ar.edu.unicen.integrador3.dto.response;

public record CarreraInscripcionDTO(
        Long carreraId,
        String nombre,
        Long cantidadDeInscriptos
) {}
