module app.pociagi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;

    opens app.pociagi to javafx.fxml;
    exports app.pociagi;
}