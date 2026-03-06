// Clase cliente que usa la fábrica sin saber qué base de datos hay detrás.
// Solo conoce las clases abstractas, nunca las concretas.

public class Application {

    private DatabaseFactory factory;
    private DatabaseConnection connection;
    private DatabaseQuery query;
    private DatabaseTransaction transaction;

    // Constructor 
    public Application(DatabaseFactory factory, String host, int port) {
        this.factory     = factory;       
        this.connection  = factory.createConnection(host, port);
        this.query       = factory.createQuery();
        this.transaction = factory.createTransaction();
    }

    // Simula un flujo completo de trabajo con la base de datos
    public void run() {
        factory.showFactoryInfo();
        connection.connect();
        transaction.beginTransaction();
        System.out.println(query.executeSelect("SELECT * FROM users"));
        query.executeUpdate("INSERT INTO users VALUES (1, 'Carlos')");
        transaction.commit();
        connection.disconnect();
    }
}