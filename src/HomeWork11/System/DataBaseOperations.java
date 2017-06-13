package HomeWork11.System;


import java.sql.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class DataBaseOperations {

    static Connection connection;
    static Statement statement;
    static Scanner sc = new Scanner(System.in);


    public static void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        statement = connection.createStatement();
        System.out.println("You have join to data base of users.");
    }

    public static void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void createUsersTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS user");
        statement.executeUpdate("CREATE TABLE user(userId INT PRIMARY KEY AUTOINCREMENT, email STRING, password STRING, name STRING, dataOfRegistration String)");
    }

    public static void createProductTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS product");
        statement.executeUpdate("CREATE TABLE product(idProduct INTEGER PRIMARY KEY AUTOINCREMENT , nameProduct STRING, description STRING, price DOUBLE, userId INT, dataOfCreation STRING)");
    }

    public static void createBillTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS bill");
        statement.executeUpdate("CREATE TABLE bill(idBill INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, idProduct INTEGER, quantity DOUBLE, cost DOUBLE, val DOUBLE, dataOfOrder STRING)");
    }


    public static int createUser(String email) throws SQLException, IllegalArgumentException {

        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM user");
        while (resultSet1.next()) {
            String s = resultSet1.getString("email");
            if (email.equals(s)) {
                System.out.println("You already in our system, try to login, if you remember your password.");
                //email = sc.nextLine();
            }
        }
        System.out.println("Please type your password.");
        String password = sc.nextLine();
        System.out.println("Please type your name.");
        String userName = sc.nextLine();
        java.util.Date date = new java.util.Date();


        statement.executeUpdate("INSERT INTO user VALUES('" + email + "', '" + password + "','" + userName + "','" + date.toString() + "')");
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("\t\t");
        stringBuilder.append(stringJoiner.add(email).add(password).add(userName).add("\nDate of creation:" + date.toString())).append("\n");
        System.out.println("You type next information:\nEmail\t\tPassword\t\tName\n" + stringBuilder.toString() + "Please don`t forget it.");
        ResultSet resultSet = statement.executeQuery("SELECT userId FROM user WHERE email ='" + email + "'");
        return resultSet.getInt(1);
    }
    //(userId INT PRIMARY KEY AUTOINCREMENT, email STRING, password STRING, name STRING, dataOfRegistration String)

    public static int logInSystem(String email) throws SQLException, IllegalArgumentException {
        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM system");
        int userId = 0;
        while (resultSet1.next()) {
            String e = resultSet1.getString("email");
            if (email.equals(e)) {
                System.out.println("Please type your password.");
                String password = sc.nextLine();
                String s = resultSet1.getString("password");
                if (password.equals(s)) {
                    String userName = resultSet1.getString("name");
                    userId = resultSet1.getInt("userId");
                    System.out.println("Hello " + userName);
                    break;
                } else {
                    System.out.println("Wrong password.");
                    throw new IllegalArgumentException();
                }
            } else {
                System.out.println("There`s no user with this email, you can registering in our system.");
                throw new IllegalArgumentException();
            }
        }
        return userId;
    }

    public static void createProduct(int userId) throws SQLException, IllegalArgumentException {

        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM product");

        int id = (resultSet1.getFetchSize() + 1);
        System.out.println("Please enter name of product.");
        String name = sc.nextLine();
        System.out.println("Please enter description.");
        String description = sc.nextLine();
        System.out.println("Please enter price of product.");
        Double price = sc.nextDouble();
        java.util.Date date = new java.util.Date();

        statement.executeUpdate("INSERT INTO product VALUES('" +id + "', '" + name + "','" + description + "','" + price + "','" + userId + "','" + date.toString() +  "')");
       // id INTEGER, name STRING, description STRING, price DOUBLE, userId INT, dataOfCreation STRING
    }

    public static void showProducts() throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            StringJoiner stringJoiner = new StringJoiner("\t\t");
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String price = resultSet.getString("price");
            stringBuilder.append(stringJoiner.add(id).add(name).add("\n").add(description).add("Price: ").add(price)).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void orderHandler(int userId) throws SQLException, ClassNotFoundException {
        double quantity = 1;
        System.out.println("Chose product which you want to buy(by name):");
        String nameProduct = sc.nextLine();
        ResultSet price = statement.executeQuery("SELECT price FROM product WHERE name ='" + nameProduct + "'");
        double price1 = price.getDouble("price");

        System.out.println("How much you want to buy?");
        quantity = sc.nextDouble();

        java.util.Date date = new java.util.Date();

        ResultSet set = statement.executeQuery("SELECT * FROM bill");
        int i = set.getFetchSize()+1;

        double v = quantity * price1;

        statement.executeUpdate("INSERT INTO bill VALUES('" + userId + "', '" + i + "', '"
                + nameProduct + "','" + quantity + "','" + price + "','" + v + "','" + date.toString() + "')");

        //bill(userId INT, id INTEGER, nameProduct STRING, quantity DOUBLE, cost DOUBLE, val DOUBLE, dataOfOrder STRING)
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bill WHERE id ='" + i + "'");

        String id = resultSet.getString("id");
        String product = resultSet.getString("nameProduct");
        String quantity1 = resultSet.getString("quantity");
        Double cost = resultSet.getDouble("cost");//не получаеться вытащить на экран значение, обсщитывает всё правильно.
        String val = resultSet.getString("val");
        String data = resultSet.getString("dataOfOrder");
        String userId3 = resultSet.getString("userId");
        ResultSet userName = statement.executeQuery("SELECT name FROM system WHERE userId='"+userId3+"' ");
        String user = userName.getString("name");
        System.out.printf("Bill№: %s\nHello %s\nYou order: %s\nPrice\tQuantity\tCost\n%s * %s = %s\n%s%n", id, user, product, cost.toString(), quantity1, val, data);


    }
}