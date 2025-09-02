package ar.edu.unicen.dao;


import ar.edu.unicen.entity.Factura;

import java.sql.SQLException;

public interface FacturaDao {
    public void createTable() throws SQLException;
    public void insertFactura(Factura f) throws SQLException;
}
