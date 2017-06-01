package HomeWork11;


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
    public static void close() throws SQLException{
        statement.close();
        connection.close();
    }

    public static void createUsersTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS system");
        statement.executeUpdate("CREATE TABLE system(email STRING, password STRING, name STRING)");
    }
    public static void createShopTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS product");
        statement.executeUpdate("CREATE TABLE product(id INTEGER, name STRING, description STRING, price DOUBLE)");
    }
    public static void createOrderTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS bill");
        statement.executeUpdate("CREATE TABLE bill(id INTEGER, nameUser STRING, nameProduct STRING, quantity DOUBLE, cost DOUBLE, val DOUBLE, data STRING)");
    }


    public static String createUser(String email) throws SQLException, IllegalArgumentException {

        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM system");
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

        statement.executeUpdate("INSERT INTO system VALUES('" + email + "', '" + password + "','" + userName + "')");
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("\t\t");
        stringBuilder.append(stringJoiner.add(email).add(password).add(userName)).append("\n");
        System.out.println("You type next information:\nEmail\t\tPassword\t\tName\n" + stringBuilder.toString() + "Please don`t forget it.");
        return userName;
    }
    public static String logInSystem(String email) throws SQLException, IllegalArgumentException {
        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM system");
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
                    throw new IllegalArgumentException();
                }
            } else {
                System.out.println("There`s no user with this email, you can registering in our system.");
                throw new IllegalArgumentException();
            }
        }
        return userName;
    }

    public static void createProduct(Integer id) throws SQLException, IllegalArgumentException {

        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM product");

        while (resultSet1.next()) {

            String i = resultSet1.getString("id");
            while ((id.toString()).equals(i)) {
                System.out.println("Product with this id already in our system, write new id for your product.");
                id = sc.nextInt();
            }
        }
        System.out.println("Please enter name of product.");
        String name = sc.nextLine();
        System.out.println("Please enter description.");
        String description = sc.nextLine();
        System.out.println("Please enter price of product.");
        Double price = sc.nextDouble();

        statement.executeUpdate("INSERT INTO product VALUES('" + id + "', '" + name + "','" + description + "','" + price + "')");

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

    public static void orderHandler(String nameUser) throws SQLException, ClassNotFoundException {
        double quantity = 1;
        System.out.println("Chose product which you want to buy(by name):");
        String nameProduct = sc.nextLine();
        ResultSet price = statement.executeQuery("SELECT price FROM product WHERE name ='"+nameProduct+"'");
        double price1 = price.getDouble("price");

        System.out.println("How much you want to buy?");
        quantity = sc.nextDouble();


        java.util.Date date = new java.util.Date();
        long i = Math.round(Math.random() * 1000);

        String s = date.toString();
        double v = quantity * price1;
        statement.executeUpdate("INSERT INTO bill VALUES('" + i  + "', '" + nameUser + "','"
                + nameProduct + "','" + quantity + "','" + price + "','" + v + "','" + s + "')");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bill WHERE id ='"+i+"'");

        String id = resultSet.getString("id");
        String user = resultSet.getString("nameUser");
        String product = resultSet.getString("nameProduct");
        String quantity1 = resultSet.getString("quantity");
        String cost = resultSet.getString("cost");
        String val = resultSet.getString("val");
        String data = resultSet.getString("data");
        System.out.println("Bill№: "+id+"\nHello "+user+"\nYou order: "+product+"\nPrice\tQuantity\tCost\n"+cost+" * "+quantity1+" = "+val+"\n"+data);


    }
}