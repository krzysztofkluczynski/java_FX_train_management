package app.pociagi.db.Utils;

import app.pociagi.db.Objects.Seat;
import app.pociagi.db.Objects.Station;

import java.sql.SQLException;

public class FindSeat {
    public static Seat findByID(Integer id) {
        try {
            Integer rideId = Integer.parseInt(Finder.find("USERS", "RIDE_ID", "SEAT_ID", id));
            Integer carNumber = Integer.parseInt(Finder.find("USERS", "CAR_NUMBER", "SEAT_ID", id));
            Integer seatNumber = Integer.parseInt(Finder.find("USERS", "SEAT_NUMBER", "SEAT_ID", id));
            Integer ticketId = Integer.parseInt(Finder.find("USERS", "TICKET_ID", "SEAT_ID", id));
            return new Seat(id, rideId, carNumber, seatNumber, ticketId);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}
