import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabaseConnection {
    public static Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/SwingDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "158963";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String[]> getBookFromDB() {
        ArrayList<String[]> listBooks = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Books");

            while (result.next()) {
                String[] books = new String[]{
                        String.valueOf(result.getInt("id")),
                        result.getString("title"),
                        result.getString("isbn"),
                        result.getString("description")};
                listBooks.add(books);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listBooks;
    }
}

