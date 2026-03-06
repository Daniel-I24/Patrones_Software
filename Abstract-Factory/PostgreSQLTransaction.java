// Clase que implementa el manejo de transacciones para PostgreSQL.
public class PostgreSQLTransaction extends DatabaseTransaction {

    // Implementación de beginTransaction() para PostgreSQL
    @Override
    public void beginTransaction() {
        System.out.println("[PostgreSQL] Transacción iniciada (BEGIN).");
    }

    // Implementación de commit() para PostgreSQL
    @Override
    public void commit() {
        System.out.println("[PostgreSQL] Transacción confirmada (COMMIT).");
    }

    // Implementación de rollback() para PostgreSQL
    @Override
    public void rollback() {
        System.out.println("[PostgreSQL] Transacción cancelada (ROLLBACK).");
    }
}