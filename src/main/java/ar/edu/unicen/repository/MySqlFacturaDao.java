package ar.edu.unicen.repository;

import ar.edu.unicen.dao.FacturaDao;
import ar.edu.unicen.entity.Cliente;
import ar.edu.unicen.entity.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlFacturaDao implements FacturaDao {
    private Connection connection;

    public MySqlFacturaDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws SQLException {
        String table =  "CREATE TABLE IF NOT EXISTS factura(" +
                        "id INT, " +
                        "id_cliente INT, " +
                        "PRIMARY KEY(id), " +
                        "FOREIGN KEY(id_cliente) REFERENCES cliente(id))";
        PreparedStatement ps = connection.prepareStatement(table);
        ps.execute();
    }

    @Override
    public void insertFactura(Factura f) throws SQLException{
        String insert = "INSERT INTO factura (id, id_cliente) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setInt(1, f.getId());
        ps.setInt(2, f.getIdCliente());
        ps.executeUpdate();
        ps.close();
    }

}
