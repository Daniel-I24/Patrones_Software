// Clase que implementa las consultas específicas para MySQL.
public class MySQLQuery extends DatabaseQuery {

    // Implementación del método executeSelect() para MySQL
    @Override
    public String executeSelect(String sql) {
        // Simulamos el resultado de una consulta SELECT
        System.out.println("[MySQL] Ejecutando SELECT: " + sql);
        return "[MySQL] Resultado simulado de: " + sql;
    }

    // Implementación del método executeUpdate() para MySQL
    @Override
    public int executeUpdate(String sql) {
        // Simulamos que se afectó 1 fila
        System.out.println("[MySQL] Ejecutando UPDATE/INSERT/DELETE: " + sql);
        return 1;
    }
}