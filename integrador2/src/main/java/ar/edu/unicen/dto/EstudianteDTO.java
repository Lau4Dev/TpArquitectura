package ar.edu.unicen.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteDTO {
    private int dni;
    private String nombreYApellido;
    private String genero;
    private String ciudad;
    private int numeroLibreta;

}
