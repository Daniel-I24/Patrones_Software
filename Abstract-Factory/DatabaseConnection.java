// Clase abstracta que representa una conexión a una base de datos
public abstract class DatabaseConnection {

    // Atributo que indica si la conexión está activa o no
    protected boolean connected = false;

    // Establece la conexión a la base de datos
    public abstract void connect();

    // Cierra la conexión a la base de datos
    public abstract void disconnect();

    // Método concreto: retorna true si la conexión está activa
    public boolean isConnected() {
        return connected;
    }
}