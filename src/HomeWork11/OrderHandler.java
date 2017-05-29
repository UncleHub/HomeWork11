package HomeWork11;


import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class OrderHandler {
    static Connection connection;
    static Statement statementOrder;
    static IllegalArgumentException ex = new IllegalArgumentException();
    static Scanner sc = new Scanner(System.in);

    public static void getConnectionOrder() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        statementOrder = connection.createStatement();
        System.out.println("You have join to data base of order.");
    }

    public static void createOrderTable() throws SQLException {
        statementOrder.executeUpdate("DROP TABLE IF EXISTS order");
        statementOrder.executeUpdate("CREATE TABLE order(id INTEGER, nameUser STRING, nameProduct STRING, quantity DOUBLE, cost DOUBLE, val DOUBLE, data STRING)");
    }


    public static void orderHandler(String nameUser) throws SQLException, ClassNotFoundException {
        double quantity = 1;
        System.out.println("Chose product which you want to buy(by name):");
        String nameProduct = sc.nextLine();
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        Statement statementShop = connection.createStatement();
        ShopHandler.getConnectionShop();
        ResultSet resultSet1 = statementShop.executeQuery("SELECT * FROM product");
        while (resultSet1.next()) {
            String s = resultSet1.getString("name");
            if (nameProduct.equals(s)) {
                System.out.println("How much you want to buy?");
                quantity = sc.nextDouble();
            } else {
                System.out.println("There`s no such product :'(");
                throw ex;
            }
        }
        double price = resultSet1.getDouble("price");
        Date date = new Date();

        statementOrder.executeUpdate("INSERT INTO order VALUES('" + statementOrder.getFetchSize() + "', '" + nameUser + "','"
                + nameProduct + "','" + quantity + "','" + price + "','" + quantity * price + "','" + date.toString() + "')");
        ResultSet resultSet = statementOrder.executeQuery("SELECT * FROM order");

        String id = resultSet.getString("id");
        String user = resultSet.getString("nameUser");
        String product = resultSet.getString("nameProduct");
        String quantity1 = resultSet.getString("quantity");
        String cost = resultSet.getString("cost");
        String val = resultSet.getString("val");
        String data = resultSet.getString("data");
        System.out.println("Bill№: "+id+"\nHello "+user+"\nYou order: "+product+"\n Price\tQuantity\tCost"+cost+"\t*"+quantity1+"\t="+val+"\n"+data);


    }
}
