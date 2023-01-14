package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.Single.FindUser;
import app.pociagi.db.Utils.Hash;
import app.pociagi.mainApp;
import app.pociagi.utils.AppData;
import app.pociagi.db.Utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LoginController {

    public LoginController() {
    }
        AppData appdata = AppData.getInstance();
          @FXML
          private Button logInButton, goBackButton;

          @FXML
          private Label wrongLogin, wrongLogin2;

          @FXML
          private TextField username;

          @FXML
          private PasswordField password;

          public void UserLogIn(ActionEvent event) throws IOException, InterruptedException, SQLException {
              try {
                  checkLogin();
                  TimeUnit.SECONDS.sleep(1);
                  appdata.user = FindUser.findByLogin(username.getText());
                  SceneChanger.changeScene(event, "main_menuv2.fxml");
              } catch(Exception ex){
                    wrongLogin.setTextFill(Color.RED);
                    wrongLogin.setText(ex.getMessage());
                    wrongLogin.setWrapText(true);
                    wrongLogin2.setWrapText(true);
                    wrongLogin2.setText("Try again!");
              }

          }

          public boolean checkLogin() throws IOException, Exception {
              mainApp m = new mainApp();
              String typed_login = username.getText().toString();
              String typed_password = null;
              try {
                  typed_password = Hash.hashPassword(password.getText().toString());
              } catch (NoSuchAlgorithmException e) {
                  throw new RuntimeException(e);
              }
              DatabaseHandler handle = DatabaseHandler.getInstance();
              String sql_query = String.format("SELECT PASSWORD FROM USERS WHERE LOGIN = '%s'", typed_login);
              ResultSet rs = handle.executeQuery(sql_query);
              ArrayList<String> arr = handle.returnDataArray(rs, 1);
              if(typed_login.isEmpty() || typed_password.isEmpty()) {
                  throw new Exception("You need to enter password and login!");
              }
              else if(arr.isEmpty()) {
                  wrongLogin.setTextFill(Color.RED);
                  throw new Exception("There is no such user!");
              }
              else if(typed_password.equals(arr.get(0))) {
                  wrongLogin.setText("Sucess!");
                  return true;
              } else {
                  throw new Exception("Wrong password!");
              }

          }

          public void goBackButtonPushed(ActionEvent event) throws IOException {
                SceneChanger.changeScene(event, "main_menuv2.fxml");
          }

          public void registerButtonPushed(ActionEvent event) throws IOException {
              SceneChanger.changeScene(event, "registration_menu.fxml");
          }


}
