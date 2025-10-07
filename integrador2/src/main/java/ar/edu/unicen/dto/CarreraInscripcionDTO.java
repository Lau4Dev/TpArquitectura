package ar.edu.unicen.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarreraInscripcionDTO {
    private String nombre;
    private Long cantidadDeInscriptos;
}
