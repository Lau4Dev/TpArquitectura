package ar.edu.unicen.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class EstudianteCarrera {
    @Id
    @Column(name = "id_estudianteCarrera")
    private int id;

    @ManyToOne
    @JoinColumn(name = "dni")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id")
    private Carrera carrera;

    private int inscripcion;
    private int graduacion;
    private int antiguedad;

}
