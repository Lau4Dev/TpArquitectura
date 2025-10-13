package ar.edu.unicen.integrador3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class EstudianteCarrera {
    @Id
    @Column(name = "id_estudianteCarrera")
    private Long id;

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
