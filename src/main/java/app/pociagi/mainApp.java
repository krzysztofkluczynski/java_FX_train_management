package app.pociagi;

import app.pociagi.db_classes_singletons.Connection;
import app.pociagi.db_classes_singletons.DBObject;
import app.pociagi.db_classes_singletons.Seat;
import app.pociagi.utils.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        Connection con = new Connection(2, "Warszawa", "Wrocław");
        Seat seat = new Seat(9004, 12, 4, 5, 5);
        ArrayList<DBObject> arr = new ArrayList<>();
        arr.add(con);
        arr.add(seat);
        seat.pushToDB();
//        DatabaseHandler handler = DatabaseHandler.getInstance();
//        String sql = "SELECT * FROM STATIONS";
//        ResultSet rs = handler.executeQuery(sql);
//        handler.printData(rs);
//        launch();
//        handler.finish();
    }
}