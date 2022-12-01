package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
//import javafx.scene.control.Label;

public class menuController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button logMenuButton;
    public AutoCompleteTextField fromText;

    @FXML
    public void userWantToLogIn(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "login_menu.fxml");
//        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }
    @FXML
    public void onHelloButtonClick(ActionEvent event) {
        System.out.println("wyszukiwarka polaczen");
    }
    public void onKeyTyped(KeyEvent event) {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 2);
        AutoCompleteTextField field = (AutoCompleteTextField) event.getSource();
        field.getEntries().addAll(arr);
    }
}