package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginController {
    public LoginController() {
    }
          @FXML
          private Button logInButton;

          @FXML
          private Label wrongLogin;

          @FXML
          private TextField username;

          @FXML
          private PasswordField password;

          public void userLogIn(ActionEvent event) throws IOException {
              checkLogin();
          }

          public void checkLogin() throws IOException {
              mainApp m = new mainApp();
              if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                  wrongLogin.setTextFill(Color.GREEN);
                  wrongLogin.setText("Sucess!");

              } else if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                  wrongLogin.setText("You need to enter login and password!");

              } else {
                  wrongLogin.setText("wrong username or password!");
              }

          }

}
