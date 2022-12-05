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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
//import javafx.scene.control.Label;

public class menuController implements Initializable {
    AppData appdata = AppData.getInstance();


    @FXML
    private Button logInButton, findConnectionButton;

    @FXML
    public AutoCompleteTextField fromWhereTextField, toWhereTextField;

    @FXML
    private Label helloLabel;



    @FXML
    public void logInButtonPushed(ActionEvent event) {
            if(ValidateSingletons.checkUser() == true) {
                appdata.user = null;
                logInButton.setText("LOG IN");
                SceneChanger.changeScene(event, "main_menuv2.fxml");
            } else {
                SceneChanger.changeScene(event, "login_menu.fxml");
            }
    }
    @FXML
    public void findConnectionButtonPushed(ActionEvent event)
    {
        appdata.ride = new Ride(fromWhereTextField.getText().toString(), toWhereTextField.getText().toString());
        SceneChanger.changeScene(event,"available_rides.fxml" );

    }
    public void onKeyTyped(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 2);
        System.out.println(arr);
        AutoCompleteTextField field = new AutoCompleteTextField();
        fromWhereTextField.getEntries().addAll(arr);
        toWhereTextField.getEntries().addAll(arr);
        if(ValidateSingletons.checkUser() == false) {
            helloLabel.setText("Hello, unknown!");
            logInButton.setText("LOG IN");
        } else {
            helloLabel.setText(String.format("Hello, %s", appdata.user.getName().toString()));
            logInButton.setText("LOG OUT");
        }
    }
}