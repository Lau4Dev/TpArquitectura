package ar.edu.unicen.dao;

import ar.edu.unicen.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao{

    void createTable() throws SQLException;
    void insertCliente(Cliente c) throws SQLException;
    void deleteCliente(Cliente c) throws SQLException;
    void deleteAllClientes() throws SQLException;
    List<Cliente> getClientesOrdernados() throws SQLException;
}
