package HomeWork11.entity;

/**
 * Created by Serega on 13.06.2017.
 */
public class Bill {
    int userId;
    int productId;
    double quantity;

    @Override
    public String toString() {
        return "Bill{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Bill(int userId, int productId, double quantity) {

        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
