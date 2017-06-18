package HomeWork11.service;

import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.User;
import java.sql.SQLException;
import java.util.HashMap;

public class LogInService {
    public boolean register(User user) throws SQLException {

        DataBase dataBase = new DataBase();

        HashMap<String,Object> userMap = new HashMap<>();
        userMap.put("name",user.getName());
        userMap.put("password",user.getPassword());

        return dataBase.select("user", userMap);

    }
}
