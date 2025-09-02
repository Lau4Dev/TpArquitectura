package ar.edu.unicen.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacturaProducto {
    private int idFactura;
    private int idProducto;
    private int cantidad;
}
