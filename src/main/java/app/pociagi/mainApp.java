package app.pociagi;

import app.pociagi.utils.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class mainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_menuv2.fxml"));
        stage.setTitle("Hello!");
        stage.setResizable(false); //jesli zaczniemy rozciagac okno to bedzie brzydkie, do poprawy
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        boolean if_user_logged = false;
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        handler.printData(rs);
        launch();
        handler.finish();
    }
}