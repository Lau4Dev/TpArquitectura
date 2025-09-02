package ar.edu.unicen.dao;

import ar.edu.unicen.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao{

    public void createTable() throws SQLException;
    public void insertCliente(Cliente c) throws SQLException;
    public List<Cliente> getClientesOrdernados() throws SQLException;
}
