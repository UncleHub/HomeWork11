package HomeWork11.entity;

public class Product {

    String nameProd;
    String descriptionProd;
    Double price;
    Integer idProd;



    public Product(String nameProd, String descriptionProd, Double price) {

        this.nameProd = nameProd;
        this.descriptionProd = descriptionProd;
        this.price = price;
    }

    public Product() {
        this.nameProd = nameProd;
        this.descriptionProd = descriptionProd;
        this.price = price;
        this.idProd = idProd;
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



    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nameProd='" + nameProd + '\'' +
                ", descriptionProd='" + descriptionProd + '\'' +
                ", price=" + price +
                '}';
    }
}
