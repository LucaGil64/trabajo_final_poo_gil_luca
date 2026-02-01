package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorDeConexion {

    private static GestorDeConexion instancia;
    private Connection conexion;

    // URL de conexión para SQLite. "src/db/jugadores.db" será el nombre del fichero.
    private static final String URL = "jdbc:sqlite:src/db/jugadores.db";

    private GestorDeConexion() {
        try {
            this.conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión con la base de datos SQLite establecida.");
            inicializarBaseDeDatos();

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static synchronized GestorDeConexion getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeConexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    private void inicializarBaseDeDatos() {
        // Se utiliza try-with-resources para asegurar el cierre automático del Statement.
        try (Statement stmt = this.conexion.createStatement()) {
            
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS jugadores (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    "nombre TEXT NOT NULL," +
                                    "puntos INTEGER NOT NULL);";
            stmt.execute(sqlCreateTable);
            System.out.println("Tabla 'jugadores' creada o ya existente.");
        
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}

