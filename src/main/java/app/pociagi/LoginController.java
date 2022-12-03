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
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginController {

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
              String typed_login = username.getText().toString();
              String typed_password = password.getText().toString();
              DatabaseHandler handle = DatabaseHandler.getInstance();
              String sql_query = String.format("SELECT PASSWORD FROM USERS WHERE LOGIN = '%s'", typed_login);
              ResultSet rs = handle.executeQuery(sql_query);
              ArrayList<String> arr = handle.returnDataArray(rs, 1);
              if(typed_login.isEmpty() || typed_password.isEmpty()) {
                  wrongLogin.setTextFill(Color.RED);
                  wrongLogin.setText("You need to enter password and login!");
              }
              else if(arr.isEmpty()) {
                  wrongLogin.setTextFill(Color.RED);
                  wrongLogin.setText("There is no such user");
              }
              else if(typed_password.equals(arr.get(0))) {
                  wrongLogin.setTextFill(Color.GREEN);
                  wrongLogin.setText("Sucess!");
              } else {
                  wrongLogin.setTextFill(Color.RED);
                  wrongLogin.setText("wrong password!");
              }

          }

          public void goBackButtonPushed(ActionEvent event) throws IOException {
                SceneChanger.changeScene(event, "main_menu.fxml");
//              Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
//              stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//              scene = new Scene(root);
//              stage.setScene(scene);
//              stage.show();
          }

          public void registerButtonPushed(ActionEvent event) throws IOException {
              SceneChanger.changeScene(event, "registration_menu.fxml");
//              Parent root = FXMLLoader.load(getClass().getResource("registration_menu.fxml"));
//              stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//              scene = new Scene(root);
//              stage.setScene(scene);
//              stage.show();
          }

}
