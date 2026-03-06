public abstract class DatabaseTransaction {
    
    // Inicia una transacción
    public abstract void beginTransaction();
    
    // Confirma y guarda todos los cambios de la transacción
    public abstract void commit();

    // Cancela los cambios de la transaccion 
    public abstract void rollback();
}
