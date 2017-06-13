package HomeWork11;

import HomeWork11.System.DataBaseOperations;

import java.sql.*;
import java.util.Scanner;

public class ConsoleProgram {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DataBaseOperations dataBaseOperations = new DataBaseOperations();
        Scanner sc = new Scanner(System.in);

        try {
            dataBaseOperations.getConnection();
            System.out.println("Hello.\nType 'It`s end of world' to delete all information from tables.\n1: to login.\n2: to create new account.");
            String ch = sc.nextLine();
            switch (ch) {
                case "It`s end of world":
                    dataBaseOperations.createUsersTable();
                    dataBaseOperations.createProductTable();
                    dataBaseOperations.createBillTable();
                    break;
                case "1":
                    System.out.println("Please enter your email.");
                    int logInSystem = dataBaseOperations.logInSystem(sc.nextLine());
                    System.out.println("a: to create new product.\nb: to make order.");
                    ch = sc.nextLine();
                    switch (ch) {
                        case "a":
                            dataBaseOperations.createProduct(logInSystem);
                            dataBaseOperations.close();
                            break;

                        case "b":
                            dataBaseOperations.showProducts();
                            dataBaseOperations.orderHandler(logInSystem);
                            dataBaseOperations.close();
                            break;
                        default:
                            System.out.println("WRONG!");
                            throw new IllegalArgumentException();
                    }
                    break;
                case "2":
                    System.out.println("Please enter your email.");
                    int signIn = dataBaseOperations.createUser(sc.nextLine());
                    System.out.println("a: to create new product.\nb: to make order.");
                    ch = sc.nextLine();
                    switch (ch) {
                        case "a":
                            dataBaseOperations.createProduct(signIn);
                            dataBaseOperations.close();
                            break;

                        case "b":
                            dataBaseOperations.showProducts();
                            dataBaseOperations.orderHandler(signIn);
                            dataBaseOperations.close();
                            break;

                        default:
                            System.out.println("WRONG!");
                            throw new IllegalArgumentException();
                    }
                    break;

                default:
                    System.out.println("Not found options for your command.");
                    dataBaseOperations.close();
                    throw new IllegalArgumentException();
            }
        } catch (ClassNotFoundException e){
            System.out.println("SQLITE library is not in ClassPass. Reason: " + e.getMessage());
            e.getStackTrace();
        } catch (SQLException e)        {
            System.out.println("Failed in establish connection. Reason: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
