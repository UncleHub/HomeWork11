package HomeWork11.controllers;

import HomeWork11.service.SignUpService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class SignUpController {


    @FXML
    public PasswordField passField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;

    SignUpService signUpService;


    public void cancelPress(ActionEvent actionEvent) {

    }

    public void okPress(ActionEvent actionEvent) throws SQLException {

        User user = new User(emailField.getText(), passField.getText(), nameField.getText());

        boolean isRegistered = signUpService.register(user);

        if(isRegistered){
            // open Main Pane
        }
    }
}
