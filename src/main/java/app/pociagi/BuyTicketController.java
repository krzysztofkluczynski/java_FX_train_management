package app.pociagi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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

    @FXML
    private Label price;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rideId = appData.pickedRide.get(0);
        cost = calculateCost(appData.pickedRide.get(5), 0);
        price.setText(String.format("%d,%02d zł", cost/100, cost%100));
        ResultSet rs = handler.executeQuery(String.format(
                "SELECT * FROM discounts"
        ));
        ArrayList<String> names = handler.returnDataArray(rs, 2);
        discountNames = FXCollections.observableArrayList(names);
        discountPicker.setItems(discountNames);
        discountPicker.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                discountPickChanged();
            }
        });
    }
    public static Integer calculateCost(Integer time, Integer discount) {
        Integer cost = time * 26 * (100 - discount ) / 100;
        return cost;
    }


    public void discountPickChanged() {
        System.out.println(                discountPicker.getItems().get(
                discountPicker.getSelectionModel().getSelectedIndex()));
        ResultSet rs = handler.executeQuery(String.format(
                "SELECT value FROM discounts WHERE name = '%s'",
                discountPicker.getItems().get(
                        discountPicker.getSelectionModel().getSelectedIndex()
                )));
        cost = calculateCost(appData.pickedRide.get(5),
                Integer.parseInt(handler.returnDataArray(rs, 1).get(0)));
        price.setText(String.format("%d,%02d zł", cost/100, cost%100));
    }

    public static void main (String[] args) {
        System.out.println(calculateCost(145, 51));
    }
}
