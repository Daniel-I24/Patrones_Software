// Clase que implementa el manejo de transacciones para MySQL.
public class MySQLTransaction extends DatabaseTransaction {

    // Implementación de beginTransaction() para MySQL
    @Override
    public void beginTransaction() {
        System.out.println("[MySQL] Transacción iniciada.");
    }

    // Implementación de commit() para MySQL
    @Override
    public void commit() {
        System.out.println("[MySQL] Transacción confirmada (COMMIT).");
    }

    // Implementación de rollback() para MySQL
    @Override
    public void rollback() {
        System.out.println("[MySQL] Transacción cancelada (ROLLBACK).");
    }
}