package HomeWork11.entity;


public class Product {

    String nameProd;
    String descriptionProd;
    Double price;

    @Override
    public String toString() {
        return "Product{" +
                "nameProd='" + nameProd + '\'' +
                ", descriptionProd='" + descriptionProd + '\'' +
                ", price=" + price +
                '}';
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public String getDescriptionProd() {
        return descriptionProd;
    }

    public void setDescriptionProd(String descriptionProd) {
        this.descriptionProd = descriptionProd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product(String nameProd, String descriptionProd, Double price) {

        this.nameProd = nameProd;
        this.descriptionProd = descriptionProd;
        this.price = price;
    }
}
