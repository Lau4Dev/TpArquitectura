package ar.edu.unicen.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    private int id;
    private String nombre;
    private float valor;
}
