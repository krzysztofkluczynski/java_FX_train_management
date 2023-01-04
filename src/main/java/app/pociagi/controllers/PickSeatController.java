package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Objects.Seat;
import app.pociagi.utils.AppData;
import app.pociagi.db.Utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PickSeatController implements Initializable {

    @FXML
    private Button goBackButton;
    private AppData appData = AppData.getInstance();
    private DatabaseHandler handler = DatabaseHandler.getInstance();

    public ListView<String> seatList;
    public Button confirmButton;
    private ArrayList<Seat> availSeats;
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void goBackPressed(ActionEvent e) {
        SceneChanger.changeScene(e,"buy_ticket.fxml");
    }

    public void pickPlacePressed(ActionEvent e) {
    }

    public Integer findRide(Integer connectionID) {
        return null;
    }
    public ArrayList<Seat> getAvailSeats(Integer rideID) {
        return null;
    }
}
