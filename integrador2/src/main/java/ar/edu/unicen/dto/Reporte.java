package ar.edu.unicen.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Reporte {
    private String carrera;
    private int anio;
    private Long egresados;
    private Long cantidadDeInscriptos;


}
