package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.Discount;
import app.pociagi.db.Objects.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** <h> AllFindTicket </h>
 * AllFindTicket allows to find ALL Ticket objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindTicket {

    /**
     * <h> find ALL by ride ID </h>
     * Finds all objects in TICKETS table on ride with ride id
     * @param rideId id of ride
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByRideID(Integer rideId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("TICKETS", "RIDE_ID", rideId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by user ID </h>
     * Finds all objects in TICKETS table with user id (all user's tickets)
     * @param userId id of user
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByUserID(Integer userId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("TICKETS", "USER_ID", userId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ride ID & departure station ID</h>
     * Finds all objects in TICKETS table on ride with ride id & departure station id
     * @param rideId id of ride
     * @param depStatId station id of departure
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByRideIdDepartureStatID(Integer rideId, Integer depStatId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("TICKETS", "RIDE_ID", rideId,
            "ID_DEPARTURE_STATION", depStatId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ride ID & arrival station ID </h>
     * Finds all objects in TICKETS table on ride with ride id & arrival station id
     * @param rideId id of ride
     * @param arrStatId station id of arrival
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByRideIdArrivalStatID(Integer rideId, Integer arrStatId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("TICKETS", "RIDE_ID", rideId,
                    "ID_ARRIVAL_STATION", arrStatId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    public static ArrayList<Ticket> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("TICKETS");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<Ticket> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<Ticket> ticketList= new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            Integer ticketId = Integer.parseInt(conData.get("TICKET_ID"));
            Integer rideId = Integer.parseInt(conData.get("RIDE_ID"));
            Integer depStatId = Integer.parseInt(conData.get("ID_DEPARTURE_STATION"));
            Integer arrStatId = Integer.parseInt(conData.get("ID_ARRIVAL_STATION"));
            Integer userId;
            try {
                userId = Integer.parseInt(conData.get("USER_ID"));
            }
            catch (NumberFormatException e){
                userId = null;
            }
            String name = conData.get("NAME");
            String surname = conData.get("NAZWISKO");
            if (userId == null)
                ticketList.add(new Ticket(
                        ticketId,
                        rideId,
                        depStatId,
                        arrStatId,
                        name,
                        surname));
            else ticketList.add(new Ticket(ticketId,
                    rideId,
                    depStatId,
                    arrStatId,
                    userId));
        }
        return ticketList;
    }
}
