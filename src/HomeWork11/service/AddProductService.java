package HomeWork11.service;

import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.Product;
import HomeWork11.utils.Context;

import java.util.Date;
import java.util.HashMap;

public class AddProductService {

    public boolean addProd(Product product) {

        DataBase dataBase = new DataBase();
        Date date = new Date();

        HashMap<String,Object> productMap = new HashMap<>();

        productMap.put("nameProduct", product.getNameProd());
        productMap.put("description", product.getDescriptionProd());
        productMap.put("price", product.getPrice());
        productMap.put("userId", Context.getInstance().getUser().getUserId());
        productMap.put("dataOfCreation",date.toString());


        return dataBase.insertProd( productMap);

    }
}