package ar.edu.unicen.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteResidenciaDTO {
    private String estudiante;
    private String carrera;
    private String residencia;
}
