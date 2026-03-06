public abstract class DatabaseQuery {
    // Ejecuta una consulta SELECT y retorna el resultado como texto
    public abstract String executeSelect(String sql);

    // Ejecuta INSERT, UPDATE o DELETE. Retorna el número de filas afectadas
    public abstract int executeUpdate(String sql);
}
