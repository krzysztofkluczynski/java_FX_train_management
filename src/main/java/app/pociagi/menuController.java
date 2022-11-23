package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
//import javafx.scene.control.Label;

public class menuController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button logMenuButton;

    @FXML
    public void userWantToLogIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onHelloButtonClick(ActionEvent event) {
        System.out.println("wyszukiwarka polaczen");
    }

}