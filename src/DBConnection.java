import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            String url = "jdbc:postgresql://localhost:5432/carpooling";
            String user = "postgres";
            String password = "35011956";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("PostgreSQL Connected!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
