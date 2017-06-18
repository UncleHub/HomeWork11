package HomeWork11.dataBase;


import HomeWork11.utils.Context;

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
        statement.executeUpdate("CREATE TABLE user(userId INTEGER PRIMARY KEY AUTOINCREMENT, email STRING UNIQUE , password STRING, name STRING, dataOfRegistration String)");

        statement.executeUpdate("DROP TABLE IF EXISTS product");
        statement.executeUpdate("CREATE TABLE product(idProduct INTEGER PRIMARY KEY AUTOINCREMENT , nameProduct STRING, description STRING, price DOUBLE, userId INT, dataOfCreation STRING)");

        statement.executeUpdate("DROP TABLE IF EXISTS bill");
        statement.executeUpdate("CREATE TABLE bill(idBill INTEGER PRIMARY KEY AUTOINCREMENT, userId INT, idProduct INT, quantity DOUBLE, price DOUBLE, total DOUBLE, dataOfOrder STRING)");
    }


    public static boolean insert(String name, HashMap<String, Object> userMap) throws SQLException {

        Set<Map.Entry<String, Object>> entries = userMap.entrySet();
        StringJoiner columLables = new StringJoiner(",");
        StringJoiner values = new StringJoiner("','", "'", "'");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            columLables.add(oneOfItem.getKey());
            values.add(oneOfItem.getValue().toString());
        }

        try {
            statement.executeQuery("INSERT INTO " + name + " ("+columLables+") VALUES ("+values+");");


        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return select("user",userMap);
    }

    public static boolean select(String name, HashMap<String, Object> userMap) throws SQLException {

        Set<Map.Entry<String, Object>> entries = userMap.entrySet();

        StringJoiner allCondition = new StringJoiner(" AND ");
        for (Map.Entry<String, Object> oneOfItem : entries) {
            StringJoiner condition = new StringJoiner("='","" , "'");
            condition.add(oneOfItem.getKey());
            condition.add(oneOfItem.getValue().toString());
            allCondition.add(condition.toString());
        }

        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + name + " WHERE " + allCondition + ";");

        if (resultSet.next()){
            Context instance = Context.getInstance();
            instance.setUserId(resultSet.getInt("userId"));
            return true;
        }

        return false;
    }
}

