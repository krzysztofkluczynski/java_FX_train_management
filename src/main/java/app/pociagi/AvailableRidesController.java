package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class AvailableRidesController implements Initializable {
    AppData appData = AppData.getInstance();
    ArrayList<ArrayList<Integer>> aval_conns;

    public AvailableRidesController() {}

    @FXML
    private ListView<String> avaibleRidesListView;

    @FXML
    private Label infoLabel, errorLabel;

    @FXML
    private Button goBackButton;


    public void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");
    }

    public void buyTicketButtonPushed(ActionEvent e) {
        Integer ind = avaibleRidesListView.getSelectionModel().getSelectedIndex();
        System.out.println(aval_conns.get(ind));
        appData.pickedRide = aval_conns.get(ind);
        SceneChanger.changeScene(e, "buy_ticket.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        String source = appData.ride.getSource().toString();
        String destination = appData.ride.getDestination().toString();
        aval_conns = connection_finder.find(source, destination);
        if (!aval_conns.isEmpty()){
            for (var int_list : aval_conns) {
                int hour_source = int_list.get(1);
                int minutes_source = int_list.get(2);
                int hour_distination = int_list.get(3);
                int minutes_destination = int_list.get(4);
                String str = String.format("%s, %d:%d --> %s, %d:%d", source, hour_source, minutes_source, destination, hour_distination, minutes_destination);
                System.out.println(str);
                avaibleRidesListView.getItems().add(str);
            }
        } else  {
            errorLabel.setWrapText(true);
            errorLabel.setText("There are no such connections, please go back.");
        }
    }
}
