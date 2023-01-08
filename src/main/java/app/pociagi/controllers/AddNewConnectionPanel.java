package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.All.AllFindConnection;
import app.pociagi.db.Finders.All.AllFindStation;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.DBObject;
import app.pociagi.db.Objects.Station;
import app.pociagi.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewConnectionPanel implements Initializable {
    @FXML
    private Button addConectionButton, addStopButton;
    @FXML
    private ListView allStationsListView;

    @FXML
    private TextField connectionIDTextField,
    fromTextField,
    toTextField,
    timeArrivalTextField,
    timeDepartureTextField,
    conIDfromStopsTextField;

    public static ArrayList<Station> stationsList;

    @FXML
    public void addConnectionButtonPushed(ActionEvent e) {
        try {
            int id = Integer.parseInt(connectionIDTextField.getText().toString());
            Station fromStation = FindStation.findByName(fromTextField.getText().toString());
            Station toStation = FindStation.findByName(toTextField.getText().toString());
            Connection con = new Connection(id, fromStation.getID(), toStation.getID());
            con.pushToDB();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void addStopButtonPushed(ActionEvent e ) throws ParseException {
        int stationID = allStationsListView.getSelectionModel().getSelectedIndex();
        //Station stop =  FindStation.findById(stationID);
        int conID = Integer.parseInt(conIDfromStopsTextField.getText().toString());
        //SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
        LocalTime timeArrival = LocalTime.parse( timeArrivalTextField.getText().toString());
        LocalTime timeDeparture = LocalTime.parse(timeDepartureTextField.getText().toString());
        Time tArr = Time.valueOf(timeArrival);
        Time tDep = Time.valueOf(timeDeparture);
        //long ms_arr = format.parse(timeArrival).getTime();
        //long ms_dep = format.parse(timeDeparture).getTime();
        //Time tDeparture = new Time(ms_dep);
        //Time tArrival = new Time(ms_arr);
        ConnectionStop stopToCreate = new ConnectionStop(conID, stationID, tArr, tDep);
        stopToCreate.pushToDB();
        System.out.println("test");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stationsList = new ArrayList<Station>(AllFindStation.getAll());
        System.out.println("tu");
        for (DBObject d : stationsList) {
            String a = d.toString();
            allStationsListView.getItems().add(a);
        }
    }


}
