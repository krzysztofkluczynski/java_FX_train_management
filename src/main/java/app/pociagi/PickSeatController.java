package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PickSeatController implements Initializable {

    @FXML
    private Button goBackButton;
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void goBackPressed(ActionEvent e) {
        SceneChanger.changeScene(e,"buy_ticket.fxml");
    }
}
