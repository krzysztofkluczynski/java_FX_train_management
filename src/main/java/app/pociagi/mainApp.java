package app.pociagi;

import app.pociagi.db.Finders.All.*;
import app.pociagi.db.Finders.Single.FindUser;
import app.pociagi.db.Objects.*;
import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.db.Utils.Route;
import app.pociagi.db.Utils.RouteFinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
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

    public static void main(String[] args) throws ParseException {
//        ArrayList<Discount> list = AllFindDiscount.findByValue(0);
//        for (Discount discount : list) {
//            System.out.println(String.format("%s, %s, %s", discount.getID(), discount.getName(), discount.getValue()));
//        }
//        ArrayList<SeatClass> alist = AllFindSeatClass.findByCoef(1);
//        for (SeatClass seatClass : alist) {
//            System.out.println(String.format("%s, %s, %s", seatClass.getID(), seatClass.getName(), seatClass.getCoef()));
//        }
//        ArrayList<Ride> rides = AllFindRide.findByRideDate("2022-12-01");
//        for (Ride ride : rides) {
//            System.out.println(String.format("%s, %s, %s", ride.getID(), ride.getConnectionId(), ride.getRideDateString()));
//        }
//        ArrayList<ConnectionStop> stops = AllFindStop.findByConnectionID(1);
//        for (ConnectionStop stop : stops) {
//            System.out.println(String.format("%s, %s, %s, %s",stop.getConnectionId(), stop.getStationId(), stop.getArrivalHour(), stop.getDepartureHour()));
//        }
//        ArrayList<Ticket> tickets = AllFindTicket.findByUserID(FindUser.findByLogin("rafal").getID());
//        for (Ticket ticket : tickets) {
//            System.out.println(String.format("%s, %s, %s, %s, %s", ticket.getID(), ticket.getRideId(), ticket.getDepartureStationId(), ticket.getArrivalStationId(), ticket.getUserId()));
//        }
//          Route route = new Route(1);
//          for (ConnectionStop stop : route.getStopList()) {
//            System.out.println(String.format("%s, %s, %s, %s",stop.getConnectionId(), stop.getStationId(), stop.getArrivalHour(), stop.getDepartureHour()));
//          }
//        ArrayList<Route> routes = RouteFinder.FindBetween("Wrocław Mikołajów", "Skierniewice");
//        System.out.println(routes);
//        for (Route route : routes) {
//            System.out.println(route.getStop("Wrocław Mikołajów").getDepartureHour());
//        }
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        handler.printData(rs);
        launch();
        handler.finish();
    }
}