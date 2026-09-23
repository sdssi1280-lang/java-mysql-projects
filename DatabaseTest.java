import java.sql.*;

public class DatabaseTest
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "YOUR-MYSQL-PASSWORD";

        try
        {
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to MySQL successfully!");

            connection.close();
        }
        catch(SQLException e)
        {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}