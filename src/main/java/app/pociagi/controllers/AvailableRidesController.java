package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.utils.connection_finder;
import app.pociagi.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AvailableRidesController implements Initializable {
    AppData appData = AppData.getInstance();
    ArrayList<ArrayList<Integer>> avail_cons;

    public AvailableRidesController() {}

    @FXML
    private ListView<String> availableRidesListView;

    @FXML
    private Label infoLabel, errorLabel;

    @FXML
    private Button goBackButton;

    public void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");
    }

    public void buyTicketButtonPushed(ActionEvent e) {
        System.out.println(avail_cons.get(availableRidesListView.getSelectionModel().getSelectedIndex()));
        appData.pickedConnection = avail_cons.get(availableRidesListView.getSelectionModel().getSelectedIndex());
        SceneChanger.changeScene(e, "buy_ticket.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        String source = appData.from.getName();
        String destination = appData.destination.getName();
        avail_cons = connection_finder.find(source, destination);
        if (!avail_cons.isEmpty()){
            for (var int_list : avail_cons) {
                int hour_source = int_list.get(1);
                int minutes_source = int_list.get(2);
                int hour_distination = int_list.get(3);
                int minutes_destination = int_list.get(4);
                String str = String.format("%s, %02d:%02d --> %s, %02d:%02d", source, hour_source, minutes_source, destination, hour_distination, minutes_destination);
                System.out.println(str);
                availableRidesListView.getItems().add(str);
            }
        } else  {
            errorLabel.setWrapText(true);
            errorLabel.setText("There are no such connections, please go back.");
        }
    }
}
