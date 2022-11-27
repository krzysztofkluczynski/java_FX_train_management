package app.pociagi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class mainApp extends Application {

//    private Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
//        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        stage.setTitle("Hello!");
        stage.setResizable(false); //jesli zaczniemy rozciagac okno to bedzie brzydkie, do poprawy
        stage.setScene(new Scene(root, 320, 240));
        stage.show();
    }

//    public void changeScene(String fxml) throws IOException {
//        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//        stg.getScene().setRoot(pane);
//    }

    public static void main(String[] args) {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        handler.printData(rs);
        launch();
        handler.finish();
    }
}