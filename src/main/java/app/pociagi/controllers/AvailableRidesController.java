package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.Ticket;
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
    ArrayList<Route> availRoutes;

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
        ArrayList<Connection> pckCons = new ArrayList<>();
        Connection con1 = new Connection(1, 1, 2);
        Connection con3 = new Connection(2, 10, 11);
        Connection con2 = new Connection(4, 28, 43);
        pckCons.add(con1);
        pckCons.add(con2);
        pckCons.add(con3);
        appData.pickedConnections = pckCons;
        appData.buyTicketData = new ArrayList<>();
        for (Connection con : appData.pickedConnections) {
            appData.buyTicketData.add(new Ticket(null, con.getID(),
                    appData.pickedDate,
                    con.getDepartureStationId(),
                    con.getArrivalStationId(),
                    null, 3, null, null, 0));
        }
        SceneChanger.changeScene(e, "buy_ticket.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        errorLabel.setText("");
//        String source = appData.from.getName();
//        String destination = appData.destination.getName();
//        this.availRoutes = RouteFinder.FindBetween(source, destination);
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
