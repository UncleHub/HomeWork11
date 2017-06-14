package HomeWork11.dataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class DataBase {

    static Connection connection;
    static Statement statement;
    static Scanner sc = new Scanner(System.in);


    public static void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        statement = connection.createStatement();
        System.out.println("You have join to data base of users.");
    }

    public static void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void createTables() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS user");
        statement.executeUpdate("CREATE TABLE user(userId INTEGER PRIMARY KEY AUTOINCREMENT, email STRING UNIQUE , password STRING, name STRING, dataOfRegistration String)");

        statement.executeUpdate("DROP TABLE IF EXISTS product");
        statement.executeUpdate("CREATE TABLE product(idProduct INTEGER PRIMARY KEY AUTOINCREMENT , nameProduct STRING, description STRING, price DOUBLE, userId INT, dataOfCreation STRING)");

        statement.executeUpdate("DROP TABLE IF EXISTS bill");
        statement.executeUpdate("CREATE TABLE bill(idBill INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, idProduct INT, quantity DOUBLE, price DOUBLE, total DOUBLE, dataOfOrder STRING)");
    }


    public boolean insert(String name, TreeMap<String,Object> userTree)  {
        Date date = new Date();
        try {
            statement.executeQuery("INSERT INTO "+name+" (email, password, name, dataOfRegistration)" +
                    "VALUES ('"+userTree.get("email")+"', '"+userTree.get("password")+"', '"+userTree.get("name")+"', '"+date.toString()+"');");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}