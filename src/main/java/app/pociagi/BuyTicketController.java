package app.pociagi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyTicketController implements Initializable {
    private AppData appData = AppData.getInstance();
    private DatabaseHandler handler = DatabaseHandler.getInstance();
    private Integer rideId;
    private Integer cost;
    private ObservableList<String> discountNames;

    @FXML
    private ListView<String> discountPicker;

    private ObservableList<String> classNames;

    @FXML
    private ListView<String> classPicker;

    @FXML
    private Label price, fromTime, toTime, fromLabel, toLabel, seatPicked;
    @FXML
    private Button goBack;
    public void initialize(URL url, ResourceBundle rb) {
        rideId = appData.pickedRide.get(0);
        fromTime.setText(String.format("%02d:%02d", appData.pickedRide.get(1),
                appData.pickedRide.get(2)));
        toTime.setText(String.format("%02d:%02d", appData.pickedRide.get(3),
                appData.pickedRide.get(4)));
        fromLabel.setText(appData.ride.getSource());
        toLabel.setText(appData.ride.getDestination());
        seatPicked.setText(String.format("Car: %d, Seat: %d",
                appData.buyTicketData.get("Car"),
                appData.buyTicketData.get("Seat")));
        initPicker("discounts", discountPicker);
        initPicker("classes", classPicker);
        recalculateCost();
    }
    private void initPicker(String pickerName, ListView<String> picker) {
        ResultSet rs = handler.executeQuery(String.format(
                "SELECT * FROM %s ORDER BY id", pickerName
        ));
        picker.setItems(FXCollections.observableArrayList(handler.returnDataArray(rs, 2)));
        if (appData.buyTicketData.get(pickerName) == null) {
            picker.getSelectionModel().select(0);
        } else picker.getSelectionModel().select(appData.buyTicketData.get(pickerName));
        System.out.println(appData.buyTicketData.get(pickerName));
        System.out.println(picker.getSelectionModel().getSelectedIndex());
        picker.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                recalculateCost();
            }
        });
    }

    public void recalculateCost() {
        Integer time = appData.pickedRide.get(5);
        Integer discount = getValue("discounts", discountPicker);
        Integer classCoef = getValue("classes", classPicker);
        Integer cost = time * 26 * (100 - discount ) / 100 * classCoef;
        price.setText(String.format("%d,%02d zł", cost/100, cost%100));
    }

    private Integer getValue(String pickerName, ListView<String> picker) {
        ResultSet rs = handler.executeQuery(String.format(
                "SELECT * FROM %s WHERE name = '%s'", pickerName,
                picker.getItems().get(
                        picker.getSelectionModel().getSelectedIndex()
                )));
        return Integer.parseInt(handler.returnDataArray(rs, 3).get(0));
    }

    public void goBackPressed(ActionEvent e) {
        SceneChanger.changeScene(e, "available_rides.fxml");
    }

    public void pickSeatPressed(ActionEvent e) {
        appData.buyTicketData.put("discounts", discountPicker.getSelectionModel().getSelectedIndex());
        appData.buyTicketData.put("classes", classPicker.getSelectionModel().getSelectedIndex());
        SceneChanger.changeScene(e, "pick_seat.fxml");
    }
}
