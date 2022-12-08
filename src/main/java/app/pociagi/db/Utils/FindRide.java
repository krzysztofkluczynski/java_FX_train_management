package app.pociagi.db.Utils;

import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.Ride;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** <h> FindRide </h>
 * FindRide allows to find Ride by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindRide {

    /**
     * <h> find by ride ID </h>
     * Finds object in RIDES table by ride id
     * @return Seat object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Ride findByID(Integer rideId) {
        try {
            Integer connectionId = Integer.getInteger(Finder.find("RIDES", "CONNECTION_ID", "RIDE_ID", rideId));
            String rideDate = Finder.find("RIDES", "RIDE_DATE", "RIDE_ID", rideId).substring(0,10);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(rideDate);
            return new Ride(rideId, connectionId, date);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        } catch (ParseException e) {
            System.err.format("Parse exception: %s\n", e.getMessage());
            return null;
        }
    }
}