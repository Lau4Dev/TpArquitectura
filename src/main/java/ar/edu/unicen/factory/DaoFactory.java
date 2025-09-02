package ar.edu.unicen.factory;

import ar.edu.unicen.dao.ClienteDao;
import ar.edu.unicen.dao.FacturaDao;
import ar.edu.unicen.dao.FacturaProductoDao;
import ar.edu.unicen.dao.ProductoDao;
import ar.edu.unicen.repository.MySqlDaoFactory;

public abstract class DaoFactory {
    //Nos aseguramos que haya una sola fabrica patrón SINGLETON
    private static volatile DaoFactory instance;

    public static DaoFactory getInstance(DBTYPE dbType) { // realiza un DCL = Doubled-Checked Locking (bloqueo con doble verificación)
        if (instance == null) { // 1er chequeo
            synchronized (DaoFactory.class) {
                if (instance == null) { // 2do chequeo
                    switch (dbType) {
                        case MYSQL:
                            instance = new MySqlDaoFactory();
                            break;
                        // case DERBY
                        //instance = new DerbyDAOFactory
                        //brak;
                        default:
                            throw new IllegalArgumentException("DBtype not supported: " + dbType);
                    }
                }
            }
        }
        return instance;
    }

    public abstract ClienteDao createClienteDao();
    public abstract FacturaDao createFacturaDao();
    public abstract ProductoDao createProductoDao();
    public abstract FacturaProductoDao cretaeFacturaProductoDao();
}
