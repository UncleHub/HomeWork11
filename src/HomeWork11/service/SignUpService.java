package HomeWork11.service;


import HomeWork11.dataBase.DataBase;

import java.sql.SQLException;
import java.util.TreeMap;

public class SignUpService {


    public boolean register(User user) throws SQLException {
        DataBase dataBase = new DataBase();

        TreeMap<String,Object> userTree = new TreeMap<>();
        userTree.put("name",user.getName());
        userTree.put("password",user.getPassword());
        userTree.put("email", user.getEmail());

        return  dataBase.insert("user", userTree);

    }
}
