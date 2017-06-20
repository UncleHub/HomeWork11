package HomeWork11.controllers;


import HomeWork11.entity.User;
import HomeWork11.service.LogInService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController {

    Stage stageLogIn = new Stage();
    LogInService logInService = new LogInService();

    @FXML
    public PasswordField passField;
    @FXML
    public TextField emailField;

    public void showLogIn() throws IOException {

        stageLogIn.setTitle("Log In username");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/LoginScene.fxml"));
        stageLogIn.initModality(Modality.APPLICATION_MODAL);
        Pane pane = ( Pane ) fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageLogIn.setScene(scene);
        stageLogIn.show();
    }

    public void pressOk(ActionEvent actionEvent) throws SQLException {

        User user = new User(emailField.getText(), passField.getText());
        if (logInService.register(user)){

            pressCancel(actionEvent);
        }
    }

    public void pressCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Scene scene = source.getScene();
        Stage window = ( Stage ) scene.getWindow();
        window.close();
    }
}
