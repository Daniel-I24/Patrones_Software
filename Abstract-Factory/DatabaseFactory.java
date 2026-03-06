// Toda fábrica hereda de esta clase y crea su propia familia de objetos.
public abstract class DatabaseFactory {

    // Cada fábrica crea su propio tipo de conexión
    public abstract DatabaseConnection createConnection(String host, int port);

    // Cada fábrica crea su propio tipo de consulta
    public abstract DatabaseQuery createQuery();

    // Cada fábrica crea su propio tipo de transacción
    public abstract DatabaseTransaction createTransaction();

    // Método concreto compartido por todas las fábricas
    public void showFactoryInfo() {
        System.out.println("Fábrica activa: " + this.getClass().getSimpleName());
    }
}