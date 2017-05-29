package HomeWork11;

import java.sql.*;
import java.util.Scanner;

public class EnteryPoint3 {
    public static void main(String[] args) throws SQLException {

        DataBaseOperations dataBaseOperations = new DataBaseOperations();
        ShopHandler shopHandler = new ShopHandler();
        OrderHandler orderHandler = new OrderHandler();
        Scanner sc = new Scanner(System.in);

        try {
            dataBaseOperations.getConnectionUser();
            shopHandler.getConnectionShop();
            orderHandler.getConnectionOrder();
            System.out.println("Hello.\nType 'It`s end of world' to delete all information from tables.\nType '1' to get in our shop to buy some things.\nType '2' create new product.");
            String ch = sc.nextLine();
            switch (ch) {
                case /*"It`s end of world"*/"0":
                    //dataBaseOperations.createUsersTable();
                    //shopHandler.createShopTable();
                    orderHandler.createOrderTable();
                    break;
                case "1":
                    System.out.println("Hello.\n1: to login.\n2: to create new account.");
                    switch (sc.nextLine()) {
                        case "1":
                            shopHandler.showProducts();
                            System.out.println("Please enter your email.");
                            orderHandler.orderHandler(dataBaseOperations.logInSystem(sc.nextLine()));

                            break;
                        case "2":
                            shopHandler.showProducts();
                            System.out.println("Please enter your email.");
                            orderHandler.orderHandler(dataBaseOperations.createUser(sc.nextLine()));
                            break;
                        default:
                            System.out.println("WRONG!");
                            IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
                            throw illegalArgumentException;
                    }
                    break;

                case "2":
                    System.out.println("Please enter identification number of product.");
                    Integer i = sc.nextInt();
                    shopHandler.createProduct(i);
                    break;
                default:
                    IllegalArgumentException e = new IllegalArgumentException();
                    System.out.println("Not found options for your command.");
                    throw e;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLITE library is not in ClassPass. Reason: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Failed in establish connection. Reason: " + e.getMessage());
        }
    }
}
