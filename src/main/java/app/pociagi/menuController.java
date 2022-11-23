package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
//import javafx.scene.control.Label;

public class menuController {

    @FXML
    private Button logMenuButton;

    @FXML
    public void userWantToLogIn(ActionEvent event) throws IOException {
        mainApp m = new mainApp();
        m.changeScene("login_menu.fxml");
    }

    public void onHelloButtonClick(ActionEvent event) {
        System.out.println("wyszukiwarka polaczen");
    }

}