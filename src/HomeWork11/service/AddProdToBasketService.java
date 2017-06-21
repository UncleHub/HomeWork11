package HomeWork11.service;

import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.Product;
import HomeWork11.utils.Context;

import java.util.Date;
import java.util.HashMap;

public class AddProdToBasketService {

    public boolean add(Product product) {

        double quantity = 1;

        DataBase dataBase = new DataBase();
        Date date = new Date();

        HashMap<String,Object> productMap = new HashMap<>();

        productMap.put("idProduct", product.getIdProd());
        productMap.put("quantity", quantity);
        productMap.put("price", product.getPrice());
        productMap.put("userId", Context.getInstance().getUser().getUserId());
        productMap.put("dataOfOrder",date.toString());
        productMap.put("total",quantity*product.getPrice());

       return dataBase.getBill(productMap);
    }
}
