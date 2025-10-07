package ar.edu.unicen.dao;

import ar.edu.unicen.entity.FacturaProducto;

import java.sql.SQLException;

public interface FacturaProductoDao {

    void createTable() throws SQLException;
    void deleteFacturaProducto(FacturaProducto fp) throws SQLException;
    void deleteAllFacturaProductos() throws SQLException;
    void insertFacturaProducto(FacturaProducto fp) throws SQLException;
}
