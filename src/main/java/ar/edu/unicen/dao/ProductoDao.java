package ar.edu.unicen.dao;

import ar.edu.unicen.entity.Producto;

import java.sql.SQLException;

public interface ProductoDao {

    public void createTable() throws SQLException;
    public void insertProducto(Producto p) throws SQLException;
    public Producto getProductoQuemasRecaudo() throws SQLException;
}
