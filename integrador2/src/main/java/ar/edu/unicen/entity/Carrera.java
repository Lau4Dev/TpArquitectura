package ar.edu.unicen.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Carrera {
    @Id
    private int id;
    @Column(unique = true)
    private String nombre;
    private int duracion;

}
