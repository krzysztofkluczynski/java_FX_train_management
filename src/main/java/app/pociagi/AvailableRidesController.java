package app.pociagi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class AvailableRidesController {
    public AvailableRidesController() {}

    @FXML
    private ListView<String> avaibleRidesListView;
    private Label infoLabel;

    private String cityFrom;
    private String cityTo;


    // te metody raczej powinny byc wyniesione do oddzielnej klasy??
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


}
