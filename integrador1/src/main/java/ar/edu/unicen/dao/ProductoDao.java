package ar.edu.unicen.dao;

import ar.edu.unicen.entity.Producto;

import java.sql.SQLException;

public interface ProductoDao {

    void createTable() throws SQLException;
    void deleteProducto(Producto p) throws SQLException;
    void deleteAllProductos() throws SQLException;
    void insertProducto(Producto p) throws SQLException;
    Producto getProductoQuemasRecaudo() throws SQLException;
}
