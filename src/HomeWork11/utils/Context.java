package HomeWork11.utils;


public class Context {

    int userId;
    int productId;

   static Context instance;

    private Context() {
    }

    public static Context getInstance(){
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

   /* @Override
    public String toString() {
        return "Context{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }*/
}
