package HomeWork11;

import java.sql.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class EnteryPoint2 {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");

            Statement statement = connection.createStatement();

            Scanner sc = new Scanner(System.in);

            System.out.println("Hello.\nType 0 to create new table.\nType 1 if you want to register in system.\nType 2 if you want login in system.");

            String ch = sc.nextLine();
            switch (ch) {
                case "0":
                    statement.executeUpdate("DROP TABLE IF EXISTS system");
                    statement.executeUpdate("CREATE TABLE system(email STRING, password STRING, name STRING)");
                    break;

                case "1":
                    System.out.println("Please type your email.");
                    String email = sc.nextLine();

                    try {
                        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM system");
                        while (resultSet1.next()) {
                            String s = resultSet1.getString("email");
                            if (email.equals(s)) {
                                IllegalArgumentException e = new IllegalArgumentException();
                                System.out.println("You already in our system, try to login, if you remember your password.");
                                throw e;
                            }
                        }

                        System.out.println("Please type your password.");
                        String password = sc.nextLine();
                        System.out.println("Please type your name.");
                        String name = sc.nextLine();

                        statement.executeUpdate("INSERT INTO system VALUES('" + email + "', '" + password + "','" + name + "')");
                        StringBuilder stringBuilder = new StringBuilder();
                        StringJoiner stringJoiner = new StringJoiner("\t\t");
                        stringBuilder.append(stringJoiner.add(email).add(password).add(name)).append("\n");
                        System.out.println("You type next information:\nEmail\t\tPassword\t\tName\n" + stringBuilder.toString() + "Please don`t forget it.");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    System.out.println("Please type your email.");
                    email = sc.nextLine();
                    ResultSet resultSet1 = statement.executeQuery("SELECT * FROM system");
                    while (resultSet1.next()) {
                        String e = resultSet1.getString("email");
                        if (email.equals(e)) {
                            System.out.println("Please type your password.");
                            String password = sc.nextLine();
                            String s = resultSet1.getString("password");
                            if (password.equals(s)) {
                                System.out.println("Hello " + resultSet1.getString("name"));
                                break;
                            } else {
                                IllegalArgumentException e1 = new IllegalArgumentException();
                                System.out.println("Wrong password.");
                                throw e1;
                            }
                        } else {
                            IllegalArgumentException e2 = new IllegalArgumentException();
                            System.out.println("There`s no user with this email, you can registering in our system.");
                            throw e2;
                        }
                    }
                    break;

                default:
                    IllegalArgumentException e3 = new IllegalArgumentException();
                    System.out.println("Not found options for your command.");
                    throw e3;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLITE library is not in ClassPass. Reason: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Failed in establish connection. Reason: " + e.getMessage());
        }
    }
}
