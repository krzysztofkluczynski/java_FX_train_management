package app.pociagi.db.Utils;

import app.pociagi.db.Finders.All.AllFindStop;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.Station;

import java.util.ArrayList;

public class RouteFinder {
    // bez przesiadek
    public static ArrayList<Route> FindBetween(String departure, String destination) {
        Station departStat = FindStation.findByName(departure);
        Station destStat = FindStation.findByName(destination);
        ArrayList<ConnectionStop> departStopList = AllFindStop.findByStationID(departStat.getID());
        ArrayList<ConnectionStop> destStopList = AllFindStop.findByStationID(destStat.getID());
        ArrayList<Route> availRoutes = new ArrayList<>();
        for (ConnectionStop departStop : departStopList) {
            for (ConnectionStop destStop : destStopList) {
                if (departStop.getConnectionId() == destStop.getConnectionId()) {
                    availRoutes.add(new Route(departStop.getConnectionId()));
                }
            }
        }
        return availRoutes;
    }
}
