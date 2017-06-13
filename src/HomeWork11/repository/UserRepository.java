package HomeWork11.repository;

import HomeWork11.Entity.User;
import HomeWork11.System.DBOFX;

import java.util.HashMap;

/**
 * Created by ivan on 6/13/17.
 */
public class UserRepository {



    public boolean addUser(final User user) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("email",user.getEmail());

        return  DBOFX.insert("tableName", stringObjectHashMap);

    }

}
