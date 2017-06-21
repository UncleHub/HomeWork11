package HomeWork11.service;

import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.Product;

import java.util.HashMap;

public class DeleteProdService {

    public void deleteProd(Product product) {

        HashMap<String,Object> productMap = new HashMap<>();


        productMap.put("nameProduct", product.getNameProd());
        productMap.put("description", product.getDescriptionProd());
        productMap.put("price", product.getPrice());
        DataBase dataBase = new DataBase();
        dataBase.deleteProd(productMap);

    }
}
