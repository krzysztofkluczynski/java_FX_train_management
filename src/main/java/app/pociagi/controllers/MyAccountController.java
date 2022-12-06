package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.utils.tickets;
import app.pociagi.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyAccountController implements Initializable {
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
        // to zostanie uzyte po przebudownie,
        nameLabel.setText("Name: " + appdata.user.getName());
        surnameLabel.setText("Surname: " + appdata.user.getSurname());
        loginLabel.setText("Login: " + appdata.user.getLogin());
        emailLabel.setText("Email: " + appdata.user.getEmail());

//
        ArrayList<String> ride_list = tickets.ticket_list(Integer.parseInt(appdata.user.getUserID()));
        for (var s : ride_list) {
            myRidesListView.getItems().add(s);
        }

    }
}
