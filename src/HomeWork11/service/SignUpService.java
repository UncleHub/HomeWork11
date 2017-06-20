package HomeWork11.service;


import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.User;

import java.util.Date;
import java.util.HashMap;

public class SignUpService {


    public boolean register(User user) {
        DataBase dataBase = new DataBase();
        Date date = new Date();

        HashMap<String,Object> userMap = new HashMap<>();
        userMap.put("name",user.getName());
        userMap.put("password",user.getPassword());
        userMap.put("email", user.getEmail());
        userMap.put("dataOfRegistration",date.toString());

        return  dataBase.insertUser("user", userMap);

    }
}
