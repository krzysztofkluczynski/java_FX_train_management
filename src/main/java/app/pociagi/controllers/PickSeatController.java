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

        availSeats = getAvailSeats(
                findRide(appData.pickedRoute.getConnection().getID()));
        for (Seat seat : availSeats) {
            seatList.getItems().add(
                    String.format("Car: %d, Seat: %d",
                            seat.getCarNumber(),
                            seat.getSeatNumber())
            );
        }
        if (appData.buyTicketData.get("Seat_list_index") == null) {
            seatList.getSelectionModel().selectFirst();
        } else {
            seatList.getSelectionModel().select(
                    appData.buyTicketData.get("Seat_list_index")
            );
        }
    }

    public void goBackPressed(ActionEvent e) {
        SceneChanger.changeScene(e,"buy_ticket.fxml");
    }

    public void pickPlacePressed(ActionEvent e) {
        appData.buyTicketData.put("Seat_list_index", seatList.getSelectionModel().getSelectedIndex());
        appData.buyTicketData.put("Seat_id",
                availSeats.get(seatList.getSelectionModel().getSelectedIndex()).getID());

        appData.buyTicketData.put("Seat",
                availSeats.get(seatList.getSelectionModel().getSelectedIndex()).getSeatNumber());
        appData.buyTicketData.put("Car",
                availSeats.get(seatList.getSelectionModel().getSelectedIndex()).getCarNumber());
        SceneChanger.changeScene(e,"buy_ticket.fxml");
    }

    public Integer findRide(Integer connectionID) {
        ResultSet rs = handler.executeQuery(String.format(
                "SELECT ride_id FROM rides " +
                        "where connection_id = %d and ride_date = '2022-12-06'", connectionID
        ));
        return Integer.parseInt(handler.returnDataArray(rs, 1).get(0));
    }
    public ArrayList<Seat> getAvailSeats(Integer rideID) {
        // RETURNS array of seats: ex. [(0, 1), (0, 2), ...]
        //                          where (0 <- car number, 1 <- seat number)
        ResultSet rs = handler.executeQuery(String.format(
                "SELECT car_number FROM SEATS WHERE RIDE_ID = %d",
                rideID
        ));
        ArrayList<Seat> availSeats = new ArrayList<Seat>();
        ArrayList<String> cars = handler.returnDataArray(rs, 1);
        rs = handler.executeQuery(String.format(
                "SELECT seat_number FROM SEATS WHERE RIDE_ID = %d",
                rideID
        ));
        ArrayList<String> seats = handler.returnDataArray(rs, 1);
        rs = handler.executeQuery(String.format(
                "SELECT seat_id FROM SEATS WHERE RIDE_ID = %d",
                rideID
        ));
        ArrayList<String> seats_ids = handler.returnDataArray(rs, 1);
        for (int i = 0; i < cars.size(); i++) {
            availSeats.add(new Seat(
                    Integer.parseInt(seats_ids.get(i)),
                    rideID,
                    Integer.parseInt(cars.get(i)),
                    Integer.parseInt(seats.get(i))
                    ));
        }
        return availSeats;
    }
}
