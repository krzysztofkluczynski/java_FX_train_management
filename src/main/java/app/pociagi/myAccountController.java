package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class myAccountController implements Initializable {
    AppData appdata = AppData.getInstance();
    @FXML
    private Label nameLabel, surnameLabel, emailLabel, loginLabel;

    @FXML
    private ListView myRidesListView;

    public void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText("Name: " + appdata.user.getName());
        surnameLabel.setText("Surname: " + appdata.user.getSurname());
        loginLabel.setText("Login: " + appdata.user.getLogin());
        emailLabel.setText("Email: " + appdata.user.getEmail());
        myRidesListView.getItems().add("Sample String1");
        myRidesListView.getItems().add("Sample String2");
        myRidesListView.getItems().add("Sample String3");
//
//        DatabaseHandler handle = DatabaseHandler.getInstance();
//        String sql_query = String.format("SELECT  FROM USERS WHERE LOGIN = '%s'", username.getText().toString());
//        ResultSet rs = handle.executeQuery(sql_query);
//        ArrayList<String> arr = handle.returnDataArray(rs, 1);
//        String userID = arr.get(0);

    }
}
