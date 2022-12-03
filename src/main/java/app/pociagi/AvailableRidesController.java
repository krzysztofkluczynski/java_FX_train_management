package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class AvailableRidesController implements Initializable {
    RideSingleton ride = RideSingleton.getInstance();

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
        SceneChanger.changeScene(e, "main_menuv2.fxml"); //chwilowo
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        String source = ride.getRide().getSource().toString();
        String destination = ride.getRide().getDestination().toString();
        ArrayList<ArrayList<Integer>> arr = connection_finder.find(source, destination);
        if (!arr.isEmpty()){
            for (var int_list : arr) {
                int hour_source = int_list.get(1);
                int minutes_source = int_list.get(1);
                int hour_distination = int_list.get(1);
                int minutes_destination = int_list.get(1);
                String str = String.format("%s, %o:%o --> %s, %o:%o", source, hour_source, minutes_source, destination, hour_distination, minutes_destination);
                avaibleRidesListView.getItems().add(str);
            }
        } else  {
            errorLabel.setWrapText(true);
            errorLabel.setText("There are no such connections, please go back.");
        }
    }
}
