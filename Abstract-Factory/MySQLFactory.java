// Fábrica que crea la familia completa de objetos MySQL.
public class MySQLFactory extends DatabaseFactory {

    @Override
    public DatabaseConnection createConnection(String host, int port) {
        return new MySQLConnection(host, port); // Crea una conexión MySQL
    }

    @Override
    public DatabaseQuery createQuery() {
        return new MySQLQuery(); // Crea una consulta MySQL
    }

    @Override
    public DatabaseTransaction createTransaction() {
        return new MySQLTransaction(); // Crea una transacción MySQL
    }
}