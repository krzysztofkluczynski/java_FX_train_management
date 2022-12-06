package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.util.Pair;

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
                findRide(appData.pickedConnection.get(0)));
        for (Seat seat : availSeats) {
            seatList.getItems().add(
                    String.format("Car: %d, Seat: %d",
                            seat.car_number,
                            seat.sit_number)
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
                availSeats.get(seatList.getSelectionModel().getSelectedIndex()).seat_id);

        appData.buyTicketData.put("Seat",
                availSeats.get(seatList.getSelectionModel().getSelectedIndex()).sit_number);
        appData.buyTicketData.put("Car",
                availSeats.get(seatList.getSelectionModel().getSelectedIndex()).car_number);
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
                "SELECT car_number FROM SITS WHERE RIDE_ID = %d",
                rideID
        ));
        ArrayList<Seat> availSeats = new ArrayList<Seat>();
        ArrayList<String> cars = handler.returnDataArray(rs, 1);
        rs = handler.executeQuery(String.format(
                "SELECT sit_number FROM SITS WHERE RIDE_ID = %d",
                rideID
        ));
        ArrayList<String> seats = handler.returnDataArray(rs, 1);
        rs = handler.executeQuery(String.format(
                "SELECT sit_id FROM SITS WHERE RIDE_ID = %d",
                rideID
        ));
        ArrayList<String> seats_ids = handler.returnDataArray(rs, 1);
        for (int i = 0; i < cars.size(); i++) {
            availSeats.add(new Seat(
                    Integer.parseInt(cars.get(i)),
                    Integer.parseInt(seats.get(i)),
                    Integer.parseInt(seats_ids.get(i))
                    ));
        }
        return availSeats;
    }
}
