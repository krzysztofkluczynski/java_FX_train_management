module app.pociagi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens app.pociagi to javafx.fxml;
    exports app.pociagi;
    exports app.pociagi.db_classes_singletons;
    opens app.pociagi.db_classes_singletons to javafx.fxml;
    exports app.pociagi.utils;
    opens app.pociagi.utils to javafx.fxml;
    exports app.pociagi.controllers;
    opens app.pociagi.controllers to javafx.fxml;
}