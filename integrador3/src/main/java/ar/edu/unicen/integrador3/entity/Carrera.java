package ar.edu.unicen.integrador3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Carrera {
    @Id
    private Long id;
    @Column(unique = true)
    private String nombre;
    private int duracion;
}
