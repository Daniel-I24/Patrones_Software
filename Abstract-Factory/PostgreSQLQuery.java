// Clase que implementa las consultas específicas para PostgreSQL.
public class PostgreSQLQuery extends DatabaseQuery {

    // Implementación del método executeSelect() para PostgreSQL
    @Override
    public String executeSelect(String sql) {
        // PostgreSQL tiene su propia sintaxis y motor de consultas
        System.out.println("[PostgreSQL] Ejecutando SELECT: " + sql);
        return "[PostgreSQL] Resultado simulado de: " + sql;
    }

    // Implementación del método executeUpdate() para MySQL
    @Override
    public int executeUpdate(String sql) {
        // Simulamos que se afectó 1 fila
        System.out.println("[PostgreSQL] Ejecutando UPDATE/INSERT/DELETE: " + sql);
        return 1;
    }
}