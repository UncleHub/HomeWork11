package HomeWork11.Controllers;

import HomeWork11.Entity.User;
import HomeWork11.services.SigninService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignInSceane {


    @FXML
    public PasswordField passFieald;
    @FXML
    public TextField nameFealde;
    @FXML
    public TextField emailFieald;

    SigninService signinService;

    public PasswordField getPassFieald() {
        return passFieald;
    }

    public TextField getNameFealde() {
        return nameFealde;
    }

    public TextField getEmailFieald() {
        return emailFieald;
    }


    public void canclePress(ActionEvent actionEvent) {

    }

    public void okPress(ActionEvent actionEvent) {
        User user = new User(emailFieald.getText(), passFieald.getText(), nameFealde.getText());

        boolean isRegistered = signinService.register(user);

        if(isRegistered){
            // open Main Pane
        }
    }
}
