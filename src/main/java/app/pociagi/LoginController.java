package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LoginController {

    public LoginController() {
    }
        UserSingleton user = UserSingleton.getInstance();
          @FXML
          private Button logInButton, goBackButton;

          @FXML
          private Label wrongLogin;

          @FXML
          private TextField username;

          @FXML
          private PasswordField password;

          public void UserLogIn(ActionEvent event) throws IOException, InterruptedException, SQLException {
              checkLogin();
              TimeUnit.SECONDS.sleep(1);
              DatabaseHandler handle = DatabaseHandler.getInstance();
              String sql_query = String.format("SELECT USER_ID FROM USERS WHERE LOGIN = '%s'", username.getText().toString());
              ResultSet rs = handle.executeQuery(sql_query);
              ArrayList<String> arr = handle.returnDataArray(rs, 1);
              String userID = arr.get(0);


              sql_query = String.format("SELECT LOGIN FROM USERS WHERE LOGIN = '%s'", username.getText().toString());
              rs = handle.executeQuery(sql_query);
              arr = handle.returnDataArray(rs, 1);
              String login = arr.get(0);

              sql_query = String.format("SELECT PASSWORD FROM USERS WHERE LOGIN = '%s'", username.getText().toString());
              rs = handle.executeQuery(sql_query);
              arr = handle.returnDataArray(rs, 1);
              String password = arr.get(0);

              sql_query = String.format("SELECT NAME FROM USERS WHERE LOGIN = '%s'", username.getText().toString());
              rs = handle.executeQuery(sql_query);
              arr = handle.returnDataArray(rs, 1);
              String name = arr.get(0);

              sql_query = String.format("SELECT SURNAME FROM USERS WHERE LOGIN = '%s'", username.getText().toString());
              rs = handle.executeQuery(sql_query);
              arr = handle.returnDataArray(rs, 1);
              String surname = arr.get(0);

              user.setUser(new User(userID, login, password, name, surname));
              SceneChanger.changeScene(event, "main_menuv2.fxml");
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
                SceneChanger.changeScene(event, "main_menuv2.fxml");
          }

          public void registerButtonPushed(ActionEvent event) throws IOException {
              SceneChanger.changeScene(event, "registration_menu.fxml");
          }


}
