package ar.edu.unicen.dao;

import ar.edu.unicen.entity.FacturaProducto;

import java.sql.SQLException;

public interface FacturaProductoDao {

    public void createTable() throws SQLException;
    public void insertFacturaProducto(FacturaProducto fp) throws SQLException;
}
