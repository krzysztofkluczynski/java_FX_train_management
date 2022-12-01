package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public LoginController() {
    }
          @FXML
          private Button logInButton, goBackButton;

          @FXML
          private Label wrongLogin;

          @FXML
          private TextField username;

          @FXML
          private PasswordField password;

          public void UserLogIn(ActionEvent event) throws IOException {
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

          public void goBackButtonPushed(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
              stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
          }

          public void registerButtonPushed(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("registration_menu.fxml"));
              stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
          }

}
