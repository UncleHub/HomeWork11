package HomeWork11;

import HomeWork11.System.DBOFX;

import java.sql.SQLException;

/**
 * Created by ivan on 6/13/17.
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBOFX.createConnection();
        DBOFX.createBillTable();
        DBOFX.createUsersTable();
        DBOFX.createProductTable();
    }

}
