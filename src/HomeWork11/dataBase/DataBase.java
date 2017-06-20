package HomeWork11.dataBase;


import HomeWork11.entity.Product;
import HomeWork11.entity.User;
import HomeWork11.exceptions.OnlineShopException;
import HomeWork11.utils.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.*;

public class DataBase {

    static Connection connection;
    static Statement statement;
    static Scanner sc = new Scanner(System.in);


    public static void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db/peapole.sqlite");
        statement = connection.createStatement();
        System.out.println("You have join to data base of users.");
    }

    public static void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void createTables() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS user");
        statement.executeUpdate("CREATE TABLE user(userId INTEGER PRIMARY KEY AUTOINCREMENT, email STRING UNIQUE, password STRING, name STRING, dataOfRegistration String)");

        statement.executeUpdate("DROP TABLE IF EXISTS product");
        statement.executeUpdate("CREATE TABLE product(idProduct INTEGER PRIMARY KEY AUTOINCREMENT , nameProduct STRING, description STRING, price DOUBLE, userId INT, dataOfCreation STRING)");

        statement.executeUpdate("DROP TABLE IF EXISTS bill");
        statement.executeUpdate("CREATE TABLE bill(idBill INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, idProduct INT, quantity DOUBLE, price DOUBLE, total DOUBLE, dataOfOrder STRING)");
    }


    public static boolean insertUser(String name, HashMap<String, Object> userMap) {

        Set<Map.Entry<String, Object>> entries = userMap.entrySet();
        StringJoiner columLables = new StringJoiner(",");
        StringJoiner values = new StringJoiner("','", "'", "'");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            columLables.add(oneOfItem.getKey());
            values.add(oneOfItem.getValue().toString());
        }

        try {
            statement.execute("INSERT INTO " + name + " (" + columLables + ") VALUES (" + values + ");");
        } catch (SQLiteException e) {
            throw new OnlineShopException("User whith this email alrady exists.");

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return userExists(userMap);
    }

    public static boolean userExists(HashMap<String, Object> userMap) {

        Set<Map.Entry<String, Object>> entries = userMap.entrySet();

        StringJoiner allCondition = new StringJoiner(" AND ");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            StringJoiner condition = new StringJoiner("='","" , "'");
            condition.add(oneOfItem.getKey());
            condition.add(oneOfItem.getValue().toString());
            allCondition.add(condition.toString());
        }

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE " + allCondition + ";");

            if (resultSet.next()){
                Context instance = Context.getInstance();
                String name, email, pass;
                email =resultSet.getString("email");
                pass =resultSet.getString("password");
                name =resultSet.getString("name");
                int id =resultSet.getInt("userId");
                User user = new User(name,email,pass,id);
                instance.setUser(user);
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }


    public static boolean insertProd(HashMap<String, Object> userMap) {

        Set<Map.Entry<String, Object>> entries = userMap.entrySet();
        StringJoiner columLables = new StringJoiner(",");
        StringJoiner values = new StringJoiner("','", "'", "'");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            columLables.add(oneOfItem.getKey());
            values.add(oneOfItem.getValue().toString());
        }
        try {
            statement.execute("INSERT INTO product (" + columLables + ") VALUES (" + values + ");");
        } catch (SQLiteException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return  false;
    }
    public static ObservableList<Product> setTableProd(){

        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            ResultSet  resultSet = statement.executeQuery("SELECT * FROM product;");
            while (resultSet.next()){

                Product product = new Product();
                product.setIdProd(resultSet.getInt(1));
                product.setNameProd(resultSet.getString(2));
                product.setDescriptionProd(resultSet.getString(3));
                product.setPrice(resultSet.getDouble(4));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public boolean updateProd(HashMap<String, Object> productMap)  {

        Set<Map.Entry<String, Object>> entries = productMap.entrySet();

        StringJoiner allCondition = new StringJoiner(", ");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            StringJoiner condition = new StringJoiner("='","" , "'");
            condition.add(oneOfItem.getKey());
            condition.add(oneOfItem.getValue().toString());
            allCondition.add(condition.toString());
        }

        try {
            statement.execute("UPDATE product SET " + allCondition + " WHERE idProduct ="+Context.getInstance().getProduct().getIdProd()+";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteProd(HashMap<String, Object> productMap) {
        //DELETE FROM Customers
        //WHERE CustomerName='Alfreds Futterkiste';
        Set<Map.Entry<String, Object>> entries = productMap.entrySet();

        StringJoiner allCondition = new StringJoiner(", ");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            StringJoiner condition = new StringJoiner("='","" , "'");
            condition.add(oneOfItem.getKey());
            condition.add(oneOfItem.getValue().toString());
            allCondition.add(condition.toString());
        }

        try {
            statement.execute("DELETE FROM product WHERE " + allCondition + ";");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

