package ar.edu.unicen.repository;

import ar.edu.unicen.dao.ClienteDao;
import ar.edu.unicen.entity.Cliente;
import ar.edu.unicen.entity.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlClienteDao  implements ClienteDao {
    private Connection connection;

    public MySqlClienteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws SQLException {
        String table =  "CREATE TABLE IF NOT EXISTS cliente(" +
                        "id INT," +
                        "nombre VARCHAR(500)," +
                        "email VARCHAR(500)," +
                        "PRIMARY KEY(id))";
        PreparedStatement ps = connection.prepareStatement(table);
        ps.execute();
    }

    @Override
    public void insertCliente(Cliente c) throws SQLException{
        String insert = "INSERT INTO cliente (id, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setInt(1, c.getId());
        ps.setString(2, c.getNombre());
        ps.setString(3, c.getEmail());
        ps.executeUpdate();
        ps.close();
    }

    public List<Cliente> getClientesOrdernados() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        String query =  "SELECT " +
                        "c.id, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS totalFacturado " +
                        "FROM cliente c " +
                        "INNER JOIN factura f ON c.id = f.id_cliente " +
                        "INNER JOIN factura_producto fp ON f.id = fp.id_factura " +
                        "INNER JOIN producto p ON fp.id_producto = p.id " +
                        "GROUP BY c.id, c.nombre " +
                        "ORDER BY totalFacturado DESC";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            clientes.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email")
                    )
            );
        }
        return clientes;
    }
}
