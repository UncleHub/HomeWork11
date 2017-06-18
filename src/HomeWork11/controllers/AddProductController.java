package HomeWork11.controllers;

import HomeWork11.entity.Product;
import HomeWork11.service.AddProductService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductController {


    AddProductService addProductService = new AddProductService();
    Stage stageNewProd = new Stage();

    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public TextArea descriptionField;

    public void show() throws IOException {

        stageNewProd.setTitle("Add new product");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/AddProductScene.fxml"));
        stageNewProd.initModality(Modality.APPLICATION_MODAL);
        Pane pane = ( Pane ) fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageNewProd.setScene(scene);
        stageNewProd.show();
    }

    public void ok(ActionEvent actionEvent) {

        double price =Double.parseDouble(priceField.getText());
        Product product = new Product(nameField.getText(),descriptionField.getText(),price);

        boolean isAdded = addProductService.addProd(product);

    }

    public void cancel(ActionEvent actionEvent) {

        stageNewProd.close();

    }

}
