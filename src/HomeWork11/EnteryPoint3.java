package HomeWork11;

import java.sql.*;
import java.util.Scanner;

public class EnteryPoint3 {
    public static void main(String[] args) throws SQLException {

        DataBaseOperations dataBaseOperations = new DataBaseOperations();
        Scanner sc = new Scanner(System.in);

        try {
            dataBaseOperations.getConnection();
            System.out.println("Hello.\nType 'It`s end of world' to delete all information from tables.\nType '1' to get in our shop to buy some things.\nType '2' create new product.");
            String ch = sc.nextLine();
            switch (ch) {
                case "It`s end of world":
                    dataBaseOperations.createUsersTable();
                    dataBaseOperations.createShopTable();
                    dataBaseOperations.createOrderTable();
                    break;
                case "1":
                    System.out.println("Hello.\n1: to login.\n2: to create new account.");
                    switch (sc.nextLine()) {
                        case "1":
                            System.out.println("Please enter your email.");
                            String logInSystem = dataBaseOperations.logInSystem(sc.nextLine());
                            dataBaseOperations.showProducts();
                            dataBaseOperations.orderHandler(logInSystem);
                            dataBaseOperations.close();
                            break;
                        case "2":
                            System.out.println("Please enter your email.");
                            String user = dataBaseOperations.createUser(sc.nextLine());
                            dataBaseOperations.showProducts();
                            dataBaseOperations.orderHandler(user);
                            dataBaseOperations.close();
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
                    dataBaseOperations.createProduct(i);
                    dataBaseOperations.close();
                    break;
                default:
                    System.out.println("Not found options for your command.");
                    dataBaseOperations.close();
                    throw new IllegalArgumentException();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLITE library is not in ClassPass. Reason: " + e.getMessage());
            e.getStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed in establish connection. Reason: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
