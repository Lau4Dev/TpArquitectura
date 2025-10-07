package ar.edu.unicen.utils;

import ar.edu.unicen.dao.ClienteDao;
import ar.edu.unicen.dao.FacturaDao;
import ar.edu.unicen.dao.FacturaProductoDao;
import ar.edu.unicen.dao.ProductoDao;
import ar.edu.unicen.factory.DBTYPE;
import ar.edu.unicen.factory.DaoFactory;
import ar.edu.unicen.repository.MySqlDaoFactory;

import java.sql.SQLException;

public class EliminarDatos {

    private static DaoFactory factory;
    private static ClienteDao cliente;
    private static FacturaDao factura;
    private static ProductoDao producto;
    private static FacturaProductoDao facturaProducto;

    public static void run() throws SQLException {
        factory = MySqlDaoFactory.getInstance(DBTYPE.MYSQL);
        cliente = factory.createClienteDao();
        factura = factory.createFacturaDao();
        producto = factory.createProductoDao();
        facturaProducto = factory.cretaeFacturaProductoDao();

        facturaProducto.deleteAllFacturaProductos();
        factura.deleteAllFacturas();
        producto.deleteAllProductos();
        cliente.deleteAllClientes();

    }
}
