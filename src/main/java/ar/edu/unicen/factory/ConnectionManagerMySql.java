package ar.edu.unicen.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
Esta clase me va a manjer la conexión con la DB MySQL
*
*/
public final class ConnectionManagerMySql {

    private static volatile ConnectionManagerMySql instance;
    private Connection connection;

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/integrador1";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    //En el constructor creo la conexión
    private ConnectionManagerMySql() {
        try {
            System.out.println("Connected to MySQL database!");
            //Registro el driver? (investigar)
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();

            //Establezco la conexión
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Connection Failed to database!");
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    /*

    Basicamente con esto me aseguro que haya una sola instancia de la conexión.
    estoy utilizando el patron SINGLETON.
    tener una sola instancia de la conexión me asegura de que no haya problemas con las famosas transacciones de las bases de datos.
    *
    Aunque 10 hilos al mismo tiempo llamen a getInstance(), solo uno va a ejecutar new ConnectionManagerMySQL().
    Todos los demás hilos reciben la misma instancia compartida.
    Así se cumple el patrón Singleton thread-safe.
    *
    */
    public static ConnectionManagerMySql getInstance() {
        if (instance == null) {
            synchronized (ConnectionManagerMySql.class) {
                if (instance == null) {
                    instance = new ConnectionManagerMySql();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
