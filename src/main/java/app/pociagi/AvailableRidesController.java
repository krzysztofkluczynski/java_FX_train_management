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

public class AvailableRidesController implements Initializable {
    public AvailableRidesController() {}

    @FXML
    private ListView<String> avaibleRidesListView;

    @FXML
    private Label infoLabel;

    @FXML
    private Button goBackButton;

    private String cityFrom;
    private String cityTo;


    // te metody raczej powinny byc wyniesione do oddzielnej klasy, np ride z getterami i stterami??
    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getCityTo() {
        return cityTo;
    }


    public void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");
    }

    public void buyTicketButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml"); //chwilowo
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tylko testowo
        String str1 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str2 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str3 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str4 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str5 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str6 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str7 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str8 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str9 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str10 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str11 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";
        String str12 = "Wroclaw 7:00 --> Warszawa 9:00   Czas podrozy: 2h";

        avaibleRidesListView.getItems().add(str1);
        avaibleRidesListView.getItems().add(str2);
        avaibleRidesListView.getItems().addAll(str3, str4, str5, str6, str7, str8, str9, str10, str11, str12);
    }
}
