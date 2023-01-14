package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegistrationMenuController {

    public RegistrationMenuController() {}
        @FXML
        private Button createAccountButton, goBackButton;

        @FXML
        private TextField loginField, nameField, surnameField, emailField, passwordFirstField, passwordSecondField;

        @FXML
        private Label errorLoginLabel;

        public void createAccountButtonPushed(ActionEvent e) throws IOException, InterruptedException {
            try {
                String login = loginField.getText().toString();
                String password = Hash.hashPassword(passwordFirstField.getText().toString());
                String name = nameField.getText().toString();
                String surname = surnameField.getText().toString();
                String email = emailField.getText().toString();
                DatabaseHandler handler = DatabaseHandler.getInstance();
                String sql = String.format("INSERT INTO USERS (LOGIN, PASSWORD, NAME, SURNAME, EMAIL) VALUES ('%s','%s','%s','%s', '%s')", login, password, name, surname, email);
                handler.executeQuery(sql);
                handler.executeQuery("commit");
                errorLoginLabel.setTextFill(Color.GREEN);
                errorLoginLabel.setText("you have succesfully created account");
                TimeUnit.SECONDS.sleep(1);
                SceneChanger.changeScene(e, "login_menu.fxml");
            } catch (Exception ex){
                errorLoginLabel.setTextFill(Color.RED);
                errorLoginLabel.setText("Unable to create account");
            }


        }

        public void goBackButtonPushed(ActionEvent e) throws IOException{
            SceneChanger.changeScene(e, "login_menu.fxml");
        }

        private boolean checkData() {       //tutaj mozna tez dostosowac co sie dzieje w okienku
            if(loginField.getText().toString().isEmpty() || //jakies basic warunki
                    nameField.getText().toString().isEmpty() ||
                    surnameField.getText().toString().isEmpty() ||
                    emailField.getText().toString().isEmpty() ||
                    emailField.getText().toString().isEmpty() ||
                    passwordFirstField.getText().toString().isEmpty() ||
                    passwordSecondField.getText().toString().isEmpty()) {
                return false;
//            } else if(nameField.getText().toString().matches("[a-zA-Z]+") &&
//                    surnameField.getText().toString().matches("[a-zA-Z]+")) {
//                return false;
//            } else if(loginField.getText().toString().matches(".*\\s.*") && //login i haslo bez bialych znakow, regex to sprawdza
//                    passwordFirstField.getText().toString().matches(".*\\s.*") &&
//                    passwordSecondField.getText().toString().matches(".*\\s.*") &&
//                    emailField.getText().toString().matches(".*\\s.*")) {
//                return false;
            } else if(!passwordSecondField.getText().toString().equals(passwordSecondField.getText().toString())) {
                    return false;
            } else  {
                return true;
            }
        }

}
