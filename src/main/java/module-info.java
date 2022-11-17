module app.pociagi {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.pociagi to javafx.fxml;
    exports app.pociagi;
}