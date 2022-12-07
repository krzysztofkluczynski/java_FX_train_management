package app.pociagi.db.Utils;

import app.pociagi.db.Objects.Station;

import java.sql.SQLException;

public class FindStation{
    public static Station findByName(String name) {
        try {
            return new Station(Integer.parseInt(Finder.find("STATIONS", "STATION_ID",
                    "NAME", name)), name);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}
