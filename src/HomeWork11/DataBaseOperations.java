package HomeWork11;


import java.sql.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class DataBaseOperations {

    static Connection connection;
    static Statement statementUser;
    static IllegalArgumentException ex = new IllegalArgumentException();
    static Scanner sc = new Scanner(System.in);


    public static void getConnectionUser() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        statementUser = connection.createStatement();
        System.out.println("You have join to data base of users.");
    }

    public static void createUsersTable() throws SQLException {
        statementUser.executeUpdate("DROP TABLE IF EXISTS system");
        statementUser.executeUpdate("CREATE TABLE system(email STRING, password STRING, name STRING)");
    }

    public static String createUser(String email) throws SQLException, IllegalArgumentException {



        ResultSet resultSet1 = statementUser.executeQuery("SELECT * FROM system");
        while (resultSet1.next()) {
            String s = resultSet1.getString("email");
            if (email.equals(s)) {
                System.out.println("You already in our system, try to login, if you remember your password.");
                email = sc.nextLine();
            }
        }
        System.out.println("Please type your password.");
        String password = sc.nextLine();
        System.out.println("Please type your name.");
        String userName = sc.nextLine();

        statementUser.executeUpdate("INSERT INTO system VALUES('" + email + "', '" + password + "','" + userName + "')");
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("\t\t");
        stringBuilder.append(stringJoiner.add(email).add(password).add(userName)).append("\n");
        System.out.println("You type next information:\nEmail\t\tPassword\t\tName\n" + stringBuilder.toString() + "Please don`t forget it.");
        return userName;

    }

    public static String logInSystem(String email) throws SQLException, IllegalArgumentException {
        ResultSet resultSet1 = statementUser.executeQuery("SELECT * FROM system");
        String userName = null;
        while (resultSet1.next()) {
            String e = resultSet1.getString("email");
            if (email.equals(e)) {
                System.out.println("Please type your password.");
                String password = sc.nextLine();
                String s = resultSet1.getString("password");
                if (password.equals(s)) {
                    userName = resultSet1.getString("name");
                    System.out.println("Hello " + userName);
                    break;
                } else {
                    System.out.println("Wrong password.");
                    throw ex;
                }
            } else {
                System.out.println("There`s no user with this email, you can registering in our system.");
                throw ex;
            }
        }
        return userName;
    }

}