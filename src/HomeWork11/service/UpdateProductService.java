package HomeWork11.service;

import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.Product;

import java.sql.SQLException;
import java.util.HashMap;

public class UpdateProductService {
    public boolean updateProd(Product product) throws SQLException {
        DataBase dataBase = new DataBase();

        HashMap<String,Object> productMap = new HashMap<>();

        productMap.put("nameProduct", product.getNameProd());
        productMap.put("description", product.getDescriptionProd());
        productMap.put("price", product.getPrice());


        return dataBase.updateProd(productMap);

    }
}
