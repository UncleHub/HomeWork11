package HomeWork11.controllers;

import HomeWork11.entity.User;
import HomeWork11.service.SignUpService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class SignUpController {


    @FXML
    public PasswordField passField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;

    SignUpService signUpService;
    Stage stageSignUp = new Stage();

    public void showSignUpScene() throws IOException {


        stageSignUp.setTitle("Sign Up username");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/SignUpScene.fxml"));
        stageSignUp.initModality(Modality.APPLICATION_MODAL);
        Pane pane = ( Pane ) fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageSignUp.setScene(scene);
        stageSignUp.show();
    }


    public void cancelPress(ActionEvent actionEvent) {
        stageSignUp.close();
    }

    public void okPress(ActionEvent actionEvent) throws SQLException {

        User user = new User(emailField.getText(), passField.getText(), nameField.getText());

        boolean isRegistered;
        isRegistered = signUpService.register(user);

        if (isRegistered) {
            stageSignUp.close();
        }
    }
}
