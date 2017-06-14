package HomeWork11.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


    public void addProdToBasket(ActionEvent actionEvent) {

    }

    public void createNewProd(ActionEvent actionEvent) throws IOException {

        Stage stageNewProd = new Stage();
        stageNewProd.setTitle("Add new product");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/AddProductScene.fxml"));
        stageNewProd.initModality(Modality.WINDOW_MODAL);

        Pane pane = (Pane)fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageNewProd.setScene(scene);

        stageNewProd.show();
    }

    public void userLogIn(ActionEvent actionEvent) throws IOException {

        Stage stageLogIn = new Stage();
        stageLogIn.setTitle("Log In username");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/LoginScene.fxml"));
        stageLogIn.initModality(Modality.WINDOW_MODAL);

        Pane pane = (Pane)fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageLogIn.setScene(scene);

        stageLogIn.show();
    }

    public void userSignUp(ActionEvent actionEvent) throws IOException {

        Stage stageSignUp = new Stage();
        stageSignUp.setTitle("Sign Up username");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/SignUpScene.fxml"));
        stageSignUp.initModality(Modality.WINDOW_MODAL);

        Pane pane = (Pane)fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageSignUp.setScene(scene);

        stageSignUp.show();

    }

    public void userExit(ActionEvent actionEvent) {

    }

}
