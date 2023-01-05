package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Utils.Route;
import app.pociagi.db.Utils.RouteFinder;
import app.pociagi.utils.connection_finder;
import app.pociagi.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AvailableRidesController implements Initializable {
    AppData appData = AppData.getInstance();
    ArrayList<ArrayList<Integer>> avail_cons;
    ArrayList<ArrayList<Route>> availRoutes;

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
        //komentuje ta linie bo mi rzuca blad -> routfinder musi zwracac liste 2wymiarowa a nie po prostu liste
        //appData.pickedRoute = availRoutes.get(availableRidesListView.getSelectionModel().getSelectedIndex());
        SceneChanger.changeScene(e, "buy_ticket.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        String source = appData.from.getName();
        String destination = appData.destination.getName();
//        this.availRoutes = RouteFinder.FindBetween(source, destination);
//komentuje ta linie bo mi rzuca blad -> routfinder musi zwracac liste 2wymiarowa a nie po prostu liste
//        for (Route route : availRoutes) {
//            Time departureTime = route.getStop(source).getDepartureHour();
//            Time arrivalTime = route.getStop(destination).getArrivalHour();
//            String str = String.format("%s, %s --> %s, %s",
//                    source,
//                    departureTime.toString(),
//                    destination,
//                    arrivalTime.toString());
//            System.out.println(str);
//            availableRidesListView.getItems().add(str);
//        }
    }
}
