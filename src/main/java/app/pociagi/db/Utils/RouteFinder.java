package app.pociagi.db.Utils;

import app.pociagi.db.Finders.All.AllFindStop;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.Station;
import javafx.util.Pair;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RouteFinder {
    private static Pair<Connection, Time> FindAfterHour(String departure, String destination, Time departHour) {
        Station departStat = FindStation.findByName(departure);
        Station destStat = FindStation.findByName(destination);
        ArrayList<ConnectionStop> departStopList = AllFindStop.findByStationID(departStat.getID());
        ArrayList<ConnectionStop> destStopList = AllFindStop.findByStationID(destStat.getID());

        Connection bestCon = null;
        Time bestArrival = null;
        Time departureHour = null;

        for (ConnectionStop departStop : departStopList) {
            for (ConnectionStop destStop : destStopList) {
                if (departStop.getConnectionId() == destStop.getConnectionId() &
                        departStop.getDepartureHour().compareTo(destStop.getArrivalHour()) < 0
                        & departStop.getDepartureHour().compareTo(departHour) > 0) {
                    if (bestArrival == null || bestArrival.compareTo(destStop.getArrivalHour()) > 0) {
                        bestCon = new Connection(departStop.getConnectionId(), departStop.getStationId(), destStop.getStationId());
                        bestArrival = destStop.getArrivalHour();
                        departureHour = departStop.getDepartureHour();
                    }
                }
            }
        }
        return new Pair<>(bestCon, departureHour);
    }

    public static ArrayList<ArrayList<Connection>> FindBetween(String departure, String destination) {
        Station departStat = FindStation.findByName(departure);
        Station destStat = FindStation.findByName(destination);
        ArrayList<ConnectionStop> departStopList = AllFindStop.findByStationID(departStat.getID());
        ArrayList<ConnectionStop> destStopList = AllFindStop.findByStationID(destStat.getID());

        ArrayList<ArrayList<Connection>> availRoutes = new ArrayList<>();
        ArrayList<Connection> inside = new ArrayList<>();
        for (ConnectionStop departStop : departStopList) {
            for (ConnectionStop destStop : destStopList) {
                if (departStop.getConnectionId() == destStop.getConnectionId() &
                        departStop.getDepartureHour().compareTo(destStop.getArrivalHour()) < 0) {
                    inside.add(new Connection(departStop.getConnectionId(), departStop.getStationId(), destStop.getStationId()));
                    availRoutes.add(inside);
                    inside = new ArrayList<>();
                }
            }
        }
        // jesli by sie okazalo ze znalezlismy przejazd bezposredni nie szukamy przesiadek
        if (availRoutes.isEmpty() == false)
            return availRoutes;

        ArrayList<ConnectionStop> fromDeparture = new ArrayList<>();
        for (ConnectionStop departStop : departStopList) {
            ArrayList<ConnectionStop> stops = new ArrayList<>();
            stops = AllFindStop.findByConnectionID(departStop.getConnectionId());
            for (ConnectionStop stop : stops) {
                if (FindStation.findById(stop.getStationId()).getConnectionStation() == 1 & stop.getStationId() != departStat.getID()) {
                    fromDeparture.add(stop);
                }
            }
        }
        for (ConnectionStop stop : fromDeparture) {
            Pair<Connection, Time> pair = FindAfterHour(FindStation.findById(stop.getStationId()).getName(), destination, stop.getArrivalHour());
            if (pair.getKey() != null) {
                if (pair.getValue().getTime()-stop.getDepartureHour().getTime() < 90*60*1000) {
                    inside.add(new Connection(stop.getConnectionId(), departStat.getID(), stop.getStationId()));
                    inside.add(pair.getKey());
                    availRoutes.add(inside);
                    inside = new ArrayList<>();
                }
            }
        }

        return availRoutes;
    }
}


