package ar.edu.unicen.repository;

import ar.edu.unicen.dao.ProductoDao;
import ar.edu.unicen.entity.Cliente;
import ar.edu.unicen.entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlProductoDao implements ProductoDao {
    private Connection connection;

    public MySqlProductoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws SQLException {
        String table =  "CREATE TABLE IF NOT EXISTS producto(" +
                        "id INT," +
                        "nombre VARCHAR(500)," +
                        "valor FLOAT," +
                        "PRIMARY KEY(id))";
        PreparedStatement ps = connection.prepareStatement(table);
        ps.execute();
    }

    @Override
    public void insertProducto(Producto p) throws SQLException {
        String insert = "INSERT INTO producto (id, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setInt(1, p.getId());
        ps.setString(2, p.getNombre());
        ps.setFloat(3, p.getValor());
        ps.executeUpdate();
        ps.close();
    }

    public Producto getProductoQuemasRecaudo() throws SQLException{
        String query =  "SELECT " +
                        "p.id, p.nombre, p.valor, SUM(fp.cantidad * p.valor) as recaudado " +
                        "FROM producto p " +
                        "INNER JOIN factura_producto fp ON p.id = fp.id_producto " +
                        "INNER JOIN factura f ON fp.id_factura = f.id " +
                        "GROUP BY p.id, p.nombre " +
                        "ORDER BY recaudado DESC " +
                        "LIMIT 1";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Producto p = new Producto();
        while (rs.next()){
             p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getFloat("valor")
            );
        }

        return p;
    }
}
