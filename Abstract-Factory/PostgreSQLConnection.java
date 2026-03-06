// Clase concreta que implementa una conexión específica para PostgreSQL.
// Hereda de DatabaseConnection y define su comportamiento particular.
public class PostgreSQLConnection extends DatabaseConnection {

    // Almacena el nombre del host de la base de datos
    private String host;

    // Almacena el puerto de conexión (PostgreSQL usa 5432 por defecto)
    private int port;

    // Constructor
    public PostgreSQLConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // Implementación del método connect() para PostgreSQL
    @Override
    public void connect() {
        // Simulamos la conexión 
        connected = true;
        System.out.println("[PostgreSQL] Conexión establecida en " + host + ":" + port);
    }

    // Implementación del método disconnect() para PostgreSQL
    @Override
    public void disconnect() {
        connected = false;
        System.out.println("[PostgreSQL] Conexión cerrada.");
    }
}