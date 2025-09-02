package ar.edu.unicen.utils;

import ar.edu.unicen.dao.ClienteDao;
import ar.edu.unicen.dao.FacturaDao;
import ar.edu.unicen.dao.FacturaProductoDao;
import ar.edu.unicen.dao.ProductoDao;
import ar.edu.unicen.entity.Cliente;
import ar.edu.unicen.entity.Factura;
import ar.edu.unicen.entity.FacturaProducto;
import ar.edu.unicen.entity.Producto;
import ar.edu.unicen.factory.DBTYPE;
import ar.edu.unicen.factory.DaoFactory;
import ar.edu.unicen.repository.MySqlDaoFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CargarDatos {
    private static DaoFactory factory;
    private static ClienteDao cliente;
    private static FacturaDao factura;
    private static ProductoDao producto;
    private static FacturaProductoDao facturaProducto;

    //crea la conexion con la BD e instancia los DAO de las entidades
    public static void run() throws SQLException, IOException {
        factory = MySqlDaoFactory.getInstance(DBTYPE.MYSQL);
        cliente = factory.createClienteDao();
        factura = factory.createFacturaDao();
        producto = factory.createProductoDao();
        facturaProducto = factory.cretaeFacturaProductoDao();
        crearTablas();
       aniadirDatos();
    }

    //crea las tablas de cada entidad
    private static void crearTablas() throws SQLException{
        cliente.createTable();
        producto.createTable();
        factura.createTable();
        facturaProducto.createTable();
    }

    //añade los datos de los CSV a cada tabla
    private static void aniadirDatos() throws SQLException, IOException{
        cargarClientes("src/main/resources/data/clientes.csv");
        cargarProductos("src/main/resources/data/productos.csv");
        cargarFacturas("src/main/resources/data/facturas.csv");
        cargarFacturasProductos("src/main/resources/data/facturas-productos.csv");
    }

    //Obtiene los clientes ordenados por su facturacion
    public static List<Cliente> getClientesOrdenadosPorFacturacion() throws SQLException {
        return cliente.getClientesOrdernados();
    }

    //Obtiene el producto que mas recaudo, se entiende recaudacion a la cantidad * valor del producto
    public static Producto getProductoQueMasRecaudo() throws SQLException {
        return producto.getProductoQuemasRecaudo();
    }

    //carga los datos del csv clientes.csv a la tabla cliente
    private static void cargarClientes(String CSV) throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));
        for(CSVRecord row: parser) {
            Cliente c1 = new Cliente(Integer.valueOf(row.get("id_cliente")), row.get("nombre"), row.get("email"));
            cliente.insertCliente(c1);
        }
    }

    //carga los datos del csv productos.csv a la tabla producto
    private static void cargarProductos(String CSV) throws SQLException, IOException{
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));
        for(CSVRecord row: parser) {
            Producto p1 = new Producto(Integer.valueOf(row.get("id_producto")), row.get("nombre"), Float.valueOf(row.get("valor")));
            producto.insertProducto(p1);
        }
    }

    //carga los datos del csv facturas.csv a la tabla factura
    private static void cargarFacturas(String CSV) throws SQLException, IOException{
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));
        for(CSVRecord row: parser) {
            Factura f1 = new Factura(Integer.valueOf(row.get("id_factura")),Integer.valueOf(row.get("id_cliente")));
            factura.insertFactura(f1);
        }
    }

    //carga los datos del csv facturas-productos.csv a la tabla factura_producto
    private static void cargarFacturasProductos(String CSV) throws SQLException, IOException{
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));
        for(CSVRecord row: parser) {
            FacturaProducto fp1 = new FacturaProducto(Integer.valueOf(row.get("id_factura")),Integer.valueOf(row.get("id_producto")), Integer.valueOf(row.get("cantidad")));
            facturaProducto.insertFacturaProducto(fp1);
        }
    }

}
