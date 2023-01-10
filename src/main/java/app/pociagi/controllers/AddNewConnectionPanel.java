package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.All.AllFindConnection;
import app.pociagi.db.Finders.All.AllFindStation;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Finders.Single.FindStop;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.DBObject;
import app.pociagi.db.Objects.Station;
import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.utils.AppData;
import app.pociagi.utils.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

public class AddNewConnectionPanel implements Initializable {
    @FXML
    private Button addConectionButton, addStopButton, goBackButton;
    @FXML
    private ListView allStationsListView;

    @FXML
    AutoCompleteTextField new_station_name;

    @FXML
    private Label succesLabel;

    @FXML
    private TextField connectionIDTextField,
            fromTextField,
            toTextField,
            timeArrivalTextField,
            timeDepartureTextField,
            conIDfromStopsTextField,
            idTextField;

    public static ArrayList<Station> stationsList;

    private ArrayList<ConnectionStop> stops = new ArrayList<ConnectionStop>();
    private ArrayList<ConnectionStop> reversedStops = new ArrayList<ConnectionStop>();


    @FXML
    private void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }

    @FXML
    public void addConnectionButtonPushed(ActionEvent e) {
        try {
            AppData.getInstance().connection.setID(Integer.parseInt(idTextField.getText().toString()));
            AppData.getInstance().connection.setArrivalStationId(stops.get(0).getStationId());
            AppData.getInstance().connection.setDepartureStationID(stops.get(stops.size() - 1).getStationId());
            Connection reversedConnection = new Connection(AppData.getInstance().connection.getID()+1, AppData.getInstance().connection.getArrivalStationId(), AppData.getInstance().connection.getDepartureStationId());

            AppData.getInstance().connection.pushToDB();
            reversedConnection.pushToDB();

            ArrayList<Integer> stopsIdsReversed = new ArrayList<>();

            for(ConnectionStop stop : stops) {
                stopsIdsReversed.add(stop.getStationId());
            }
            Collections.reverse(stopsIdsReversed);
            int i = 0;
            for (ConnectionStop stop : stops) {
                stop.setConnectionID(AppData.getInstance().connection.getID());
                ConnectionStop sReversed = new ConnectionStop(reversedConnection.getID(), stopsIdsReversed.get(i), stop.getArrivalHour(), stop.getDepartureHour());
                stop.pushToDB();
                sReversed.pushToDB();
                i++;
            }
            new_station_name.setText("");
            timeArrivalTextField.setText("");
            timeDepartureTextField.setText("");
            idTextField.setText("");
            succesLabel.setVisible(true);
            allStationsListView.getItems().clear();
            stopsIdsReversed.clear();
            reversedStops.clear();
            stops.clear();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void addStopButtonPushed(ActionEvent e) throws ParseException {
        LocalTime timeArrival = LocalTime.parse(timeArrivalTextField.getText().toString());
        LocalTime timeDeparture = LocalTime.parse(timeDepartureTextField.getText().toString());
        Time tArr = Time.valueOf(timeArrival);
        Time tDep = Time.valueOf(timeDeparture);
        ConnectionStop stop = new ConnectionStop(AppData.getInstance().connection.getID(), FindStation.findByName(new_station_name.getText().toString()).getID(), tArr, tDep);
        stops.add(stop);    //w tym miejscu stop ma null jako connection_id i metoda to_String jest nadpisana w klasie ConnectionStop
        allStationsListView.getItems().add(stop.toString());
        new_station_name.setText("");
        timeArrivalTextField.setText("");
        timeDepartureTextField.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        succesLabel.setVisible(false);
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 2);
        AutoCompleteTextField field = new AutoCompleteTextField();
        new_station_name.getEntries().addAll(arr);
    }
}
