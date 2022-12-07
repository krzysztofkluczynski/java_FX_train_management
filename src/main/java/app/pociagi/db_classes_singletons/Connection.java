package app.pociagi.db_classes_singletons;

import java.util.HashMap;

public class Connection extends DBObject{
    private String departureStation;
    private String arrivalStation;

    public Connection(Integer id, String departureStation, String arrivalStation) {
        super(id);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", this.getID());
        dict.put("DEPARTURE_STATION", this.departureStation);
        dict.put("ARRIVAL_STATION", this.arrivalStation);
        super.data = dict;
        super.table = "CONNECTIONS";
        super.pushToDB();
    }

    public void removeFromDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", this.getID());
        super.removeFromDB("CONNECTION", dict);
    }
}
