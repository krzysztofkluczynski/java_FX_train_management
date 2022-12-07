package app.pociagi.db_classes_singletons;

import java.util.HashMap;

public class Seat extends DBObject {
    private Integer carNumber;
    private Integer seatNumber;
    private Integer rideId;
    private Boolean isOccupied;
    private Integer ticketId;

    public Seat(Integer id, Integer rideId, Integer carNumber, Integer seatNumber) {
        super(id);
        this.rideId = rideId;
        this.carNumber = carNumber;
        this.seatNumber = seatNumber;
        this.isOccupied = Boolean.FALSE;
        this.ticketId = null;
    }
    public Seat(Integer id, Integer rideId, Integer carNumber, Integer seatNumber, Integer ticketId) {
        super(id);
        this.ticketId = ticketId;
        this.rideId = rideId;
        this.carNumber = carNumber;
        this.seatNumber = seatNumber;
        this.isOccupied = Boolean.TRUE;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
        this.isOccupied = Boolean.TRUE;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public Integer getRideId() {
        return rideId;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("SEAT_ID", this.getID());
        dict.put("RIDE_ID", this.rideId);
        dict.put("CAR_NUMBER", this.carNumber);
        dict.put("SEAT_NUMBER", this.seatNumber);
        if (ticketId!=null) {dict.put("TICKET_ID", this.ticketId);
                            dict.put("IS_OCCUPIED", this.isOccupied);}
        super.data = dict;
        super.table = "SEATS";
        super.pushToDB();
    }
}
