package HomeWork11.utils;


import HomeWork11.entity.Product;
import HomeWork11.entity.User;

public class Context {

    User user;
    Product product;

   static Context instance;

    private Context() {
    }

    public static Context getInstance(){
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static void setInstance(Context instance) {
        Context.instance = instance;
    }

    /* @Override
    public String toString() {
        return "Context{" +
                "user=" + user +
                ", product=" + product +
                '}';
    }*/
}
