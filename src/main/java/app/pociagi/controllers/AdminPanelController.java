package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
    @FXML
    private Button ConnectionButton, stopsButton, userButton, addButton, editButton, deleteButton;
    @FXML
    private ListView actionsListView;

    @FXML
    private void connectionButtonPushed(ActionEvent e) {}
    @FXML
    private void stopsButtonPushed(ActionEvent e) {}
    @FXML
    private void usersButtonPushed(ActionEvent e) {}
    @FXML
    private void addButtonPushed(ActionEvent e) {}
    @FXML
    private void editButtonPushed(ActionEvent e) {}
    @FXML
    private void deleteButtonPushed(ActionEvent e) {}

    @FXML
    private void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionsListView.setVisible(false);
    }
}
