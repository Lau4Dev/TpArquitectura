package ar.edu.unicen.dao;


import ar.edu.unicen.entity.Factura;

import java.sql.SQLException;

public interface FacturaDao {
    void createTable() throws SQLException;
    void deleteFactura(Factura f) throws SQLException;
    void deleteAllFacturas() throws SQLException;
    void insertFactura(Factura f) throws SQLException;
}
