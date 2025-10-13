package ar.edu.unicen.integrador3.dto.response;

public record ReporteDTO(
         String carrera,
         int anio,
         Long egresados,
         Long cantidadDeInscriptos
) {}
