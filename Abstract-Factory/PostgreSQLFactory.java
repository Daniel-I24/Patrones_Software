// Fábrica que crea la familia completa de objetos PostgreSQL.
public class PostgreSQLFactory extends DatabaseFactory {

    @Override
    public DatabaseConnection createConnection(String host, int port) {
        return new PostgreSQLConnection(host, port); // Crea una conexión PostgreSQL
    }

    @Override
    public DatabaseQuery createQuery() {
        return new PostgreSQLQuery(); // Crea una consulta PostgreSQL
    }

    @Override
    public DatabaseTransaction createTransaction() {
        return new PostgreSQLTransaction(); // Crea una transacción PostgreSQL
    }
}