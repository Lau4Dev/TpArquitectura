package ar.edu.unicen.repository;

import ar.edu.unicen.dao.FacturaProductoDao;
import ar.edu.unicen.entity.FacturaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlFacturaProductoDao implements FacturaProductoDao {
    private Connection connection;

    public MySqlFacturaProductoDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createTable() throws SQLException {
        String table =  "CREATE TABLE IF NOT EXISTS factura_producto(" +
                        "id_factura INT, " +
                        "id_producto INT, " +
                        "cantidad INT, " +
                        "PRIMARY KEY(id_factura, id_producto), " +
                        "FOREIGN KEY (id_factura) REFERENCES factura(id), " +
                        "FOREIGN KEY (id_producto) REFERENCES producto(id))";
        PreparedStatement ps = connection.prepareStatement(table);
        ps.execute();
    }

    @Override
    public void insertFacturaProducto(FacturaProducto fp) throws SQLException {
        String insert = "INSERT INTO factura_producto (id_factura, id_producto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setInt(1, fp.getIdFactura());
        ps.setInt(2, fp.getIdProducto());
        ps.setInt(3, fp.getCantidad());
        ps.executeUpdate();
        ps.close();
    }
}
