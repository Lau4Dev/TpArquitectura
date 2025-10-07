package ar.edu.unicen;

import ar.edu.unicen.utils.CargarDatos;
import ar.edu.unicen.utils.EliminarDatos;

import java.io.IOException;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws IOException, SQLException {

        EliminarDatos.run();
        CargarDatos.run();

        System.out.println(CargarDatos.getClientesOrdenadosPorFacturacion());
        System.out.println(CargarDatos.getProductoQueMasRecaudo());
    }
}


