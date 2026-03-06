// Clase  que implementa una conexión específica para MySQL.

public class MySQLConnection extends DatabaseConnection {

    // Almacena el nombre del host de la base de datos
    private String host;

    // Almacena el puerto de conexión 
    private int port;

    // Constructor
    public MySQLConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // Implementación del método connect() para MySQL
    @Override
    public void connect() {
        // Simulamos la conexión 
        connected = true;
        System.out.println("[MySQL] Conexión establecida en " + host + ":" + port);
    }

    // Implementación del método disconnect() para MySQL
    @Override
    public void disconnect() {
        connected = false;
        System.out.println("[MySQL] Conexión cerrada.");
    }
}