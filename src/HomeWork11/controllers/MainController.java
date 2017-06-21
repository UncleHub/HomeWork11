package HomeWork11.controllers;


import HomeWork11.dataBase.DataBase;
import HomeWork11.entity.Product;
import HomeWork11.service.DeleteProdService;
import HomeWork11.utils.Context;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class MainController {

    @FXML
    public Label lblHello;
    @FXML
    public TableView prodTable;
    @FXML
    public TableColumn<Product, Integer> prodId;
    @FXML
    public TableColumn<Product, String> prodName;
    @FXML
    public TableColumn<Product, String> prodDescr;
    @FXML
    public TableColumn<Product, Double> prodPrice;

    public void setLblHello() {

        lblHello.setText("Hello " + Context.getInstance().getUser().getName());
    }

    @FXML
    public void initialize() {

        prodId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("idProd"));
        prodName.setCellValueFactory(new PropertyValueFactory<Product, String>("nameProd"));
        prodDescr.setCellValueFactory(new PropertyValueFactory<Product, String>("descriptionProd"));
        prodPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        prodTable.setItems(DataBase.setTableProd());

        DataBase.setTableProd().addListener(new ListChangeListener<Product>() {
            @Override
            public void onChanged(Change<? extends Product> c) {
               prodTable.setItems(DataBase.setTableProd());
            }
        });

        Product selectedProd = ( Product ) prodTable.getSelectionModel().getSelectedItem();

        Context.getInstance().setProduct(selectedProd);

    }

    public void createNewProd(ActionEvent actionEvent) throws IOException {
        CreateNewProdController createNewProdController = new CreateNewProdController();
        createNewProdController.show();

    }

    public void userLogIn(ActionEvent actionEvent) throws IOException {
        LogInController logInController = new LogInController();
        logInController.showLogIn();
    }

    public void userSignUp(ActionEvent actionEvent) throws IOException {
        SignUpController signUpController = new SignUpController();
        signUpController.showSignUpScene();
    }

    public void userExit(ActionEvent actionEvent) {
        Context.getInstance().setUser(null);

    }

    public void updateProd(ActionEvent actionEvent) throws IOException {
        UpdateProdController updateProdController = new UpdateProdController();
        updateProdController.show();
    }

    public void deleteProd(ActionEvent actionEvent) {

        DeleteProdService deleteService = new DeleteProdService();
        deleteService.deleteProd(Context.getInstance().getProduct());
    }

    public void addProdToBasket(ActionEvent actionEvent) {
        lblHello.setText(Context.getInstance().getProduct().toString());

       /* AddProdToBasketService addProdToBasketService = new AddProdToBasketService();
       if(addProdToBasketService.add(Context.getInstance().getProduct())){
           lblHello.setText("you make order");
       }else {
           lblHello.setText("I`m sorry your order not handle.");
       }*/

    }
}
