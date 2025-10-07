package ar.edu.unicen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Estudiante {
    @Id
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private int numeroLibreta;
}
