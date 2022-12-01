package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public RegistrationMenuController() {}
        @FXML
        private Button createAccountButton, goBackButton;

        @FXML
        private TextField loginField, nameField, surnameField, emailField, passwordFirstField, passwordSecondField;

        @FXML
        private Label errorLoginLabel;

        public void createAccountButtonPushed(ActionEvent e) throws IOException {
            boolean validate = checkData();
            if(validate) {
                Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                errorLoginLabel.setTextFill(Color.RED);
                errorLoginLabel.setText("Unable to create account");
            }


        }

        public void goBackButtonPushed(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        private boolean checkData() {
            return false;
        }

}
