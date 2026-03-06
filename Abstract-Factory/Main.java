// Punto de entrada del programa.
public class Main {

    public static void main(String[] args) {

        System.out.println("--- Usando MySQL ---");
        DatabaseFactory mysqlFactory = new MySQLFactory();
        Application app1 = new Application(mysqlFactory, "localhost", 3306);
        app1.run();

        System.out.println("\n--- Usando PostgreSQL ---");
        DatabaseFactory postgresFactory = new PostgreSQLFactory();
        Application app2 = new Application(postgresFactory, "localhost", 5432);
        app2.run();
    }
}