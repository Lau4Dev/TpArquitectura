package ar.edu.unicen.repository;

import ar.edu.unicen.dao.*;
import ar.edu.unicen.factory.ConnectionManagerMySql;
import ar.edu.unicen.factory.DaoFactory;

public class MySqlDaoFactory extends DaoFactory {

    public ClienteDao createClienteDao(){
        return new MySqlClienteDao(ConnectionManagerMySql.getInstance().getConnection());
    }

    public FacturaDao createFacturaDao(){
        return new MySqlFacturaDao(ConnectionManagerMySql.getInstance().getConnection());
    }

    public ProductoDao createProductoDao(){
        return new MySqlProductoDao(ConnectionManagerMySql.getInstance().getConnection());
    }

    public FacturaProductoDao cretaeFacturaProductoDao(){
        return new MySqlFacturaProductoDao(ConnectionManagerMySql.getInstance().getConnection());
    }
}
