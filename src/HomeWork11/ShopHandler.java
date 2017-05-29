package HomeWork11;

import java.sql.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class ShopHandler {

    static Connection connection;
    static Statement statementShop;
    static IllegalArgumentException ex = new IllegalArgumentException();
    static Scanner sc = new Scanner(System.in);

    public static void getConnectionShop() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        statementShop = connection.createStatement();
        System.out.println("You have join to data base of shop.");
    }

    public static void createShopTable() throws SQLException {
        statementShop.executeUpdate("DROP TABLE IF EXISTS product");
        statementShop.executeUpdate("CREATE TABLE product(id INTEGER, name STRING, description STRING, price DOUBLE)");
    }

    public static void createProduct(Integer id) throws SQLException, IllegalArgumentException {

        ResultSet resultSet1 = statementShop.executeQuery("SELECT * FROM product");

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

            statementShop.executeUpdate("INSERT INTO product VALUES('" + id + "', '" + name + "','" + description + "','" + price + "')");

        }

    public static void showProducts() throws SQLException {

        ResultSet resultSet = statementShop.executeQuery("SELECT * FROM product");
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
    public static void orderHandler(){
        System.out.println("Chose product which you want to buy(by ID):");
        Scanner sc = new Scanner(System.in);
        Integer id = sc.nextInt();
        System.out.println("How much you want to buy?");
        Double q = sc.nextDouble();
    }

}
