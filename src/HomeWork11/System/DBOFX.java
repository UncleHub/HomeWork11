package HomeWork11.System;


import HomeWork11.Entity.Bill;
import HomeWork11.Entity.LogInUser;
import HomeWork11.Entity.Product;
import HomeWork11.Entity.SignInUser;

import java.sql.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class DBOFX {

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
        statement.executeUpdate("CREATE TABLE bill(idBill INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, idProduct INT, quantity DOUBLE, price DOUBLE, total DOUBLE, dataOfOrder STRING)");
    }


    public static int createUser(SignInUser signInUser) throws SQLException, IllegalArgumentException {

        int userId = 0;

        String email = signInUser.getEmail();
        ResultSet resultSet1 = statement.executeQuery("SELECT email FROM user WHERE email ='" + email + "'");//проверка
        if (resultSet1.next()) {
            System.out.println("You`re already in sign in.");
        } else {
            String password = signInUser.getPassword();
            String name = signInUser.getName();
            java.util.Date date = new java.util.Date();
            statement.executeUpdate("INSERT INTO user VALUES('" + email + "', '" + password + "','" + name + "','" + date.toString() + "')");
            StringBuilder stringBuilder = new StringBuilder();
            StringJoiner stringJoiner = new StringJoiner("\t\t");
            stringBuilder.append(stringJoiner.add(email).add(password).add(name).add("\nDate of creation:" + date.toString())).append("\n");
            System.out.println("You type next information:\nEmail\t\tPassword\t\tName\n" + stringBuilder.toString() + "Please don`t forget it.");
            ResultSet resultSet = statement.executeQuery("SELECT userId FROM user WHERE email ='" + email + "'");
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    public static int logInUser(LogInUser logInUser) throws SQLException, IllegalArgumentException {
        int userId = 0;
        String email = logInUser.getEmail();
        ResultSet resultSet = statement.executeQuery("SELECT  email FROM user WHERE email ='" + email + "'");
        if (resultSet.next()) {
            String password = logInUser.getPassword();
            ResultSet resultSet1 = statement.executeQuery("SELECT userId FROM user WHERE email ='" + email + "' AND password ='" + password + "'");
            if (resultSet1.next()) {
                userId = resultSet1.getInt("userId");
            } else {
                System.out.println("Wrong password.");
                throw new IllegalArgumentException();
            }
        } else {
            System.out.println("There`s no user with this email, you can registering in our system.");
            throw new IllegalArgumentException();
        }
        return userId;
    }



    public static void createProduct(int userId, Product product) throws SQLException, IllegalArgumentException {

        String nameProd = product.getNameProd();
        String descriptionProd = product.getDescriptionProd();
        Double price = product.getPrice();
        java.util.Date date = new java.util.Date();
        statement.executeUpdate("INSERT INTO product VALUES('"+ nameProd + "','" + descriptionProd + "','" + price + "','" + userId + "','" + date.toString() + "')");

    }

public static void showProducts() throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            StringJoiner stringJoiner = new StringJoiner("\t\t");
            String id = resultSet.getString("idProduct");
            String name = resultSet.getString("nameProduct");
            String description = resultSet.getString("description");
            String price = resultSet.getString("price");
            stringBuilder.append(stringJoiner.add(id).add(name).add("\n").add(description).add("Price: ").add(price)).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void orderHandler(Bill bill) throws SQLException, ClassNotFoundException {

        int userId = bill.getUserId();
        int productId = bill.getProductId();
        double quantity = bill.getQuantity();
        java.util.Date date = new java.util.Date();
        ResultSet resultSet = statement.executeQuery("SELECT  price FROM user WHERE idProduct ='" + productId + "'");
        double price = resultSet.getDouble("price");
        double total = price*quantity;

        statement.executeUpdate("INSERT INTO bill VALUES('" + userId + "', '" + productId + "','" + quantity + "','" + price + "','" + total + "','" + date.toString() + "')");

        //idBill INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, idProduct INTEGER, quantity DOUBLE, cost DOUBLE, val DOUBLE, dataOfOrder STRING



    }
}