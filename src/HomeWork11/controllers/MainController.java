package HomeWork11.controllers;


import HomeWork11.utils.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class MainController {

    @FXML
    public Label lblHello;
    @FXML
    public TableView prodTable;
    @FXML
    public TableColumn prodId;
    @FXML
    public TableColumn prodName;
    @FXML
    public TableColumn prodDescr;
    @FXML
    public TableColumn prodPrice;


    public void addProdToBasket(ActionEvent actionEvent){
    }

    public void createNewProd(ActionEvent actionEvent) throws IOException {
        AddProductController addProductController = new AddProductController();
        addProductController.show();
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
        Context.getInstance().setUserId(0);

    }

}
