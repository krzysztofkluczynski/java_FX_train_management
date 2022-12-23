package app.pociagi.db.Objects;

import java.util.HashMap;

/**
 * <h2> Ticket </h2>
 * Representation of data from a row from TICKETS database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Ticket extends DBObject{

 private final Integer rideId;
    private final Integer departureStationId;
    private final Integer arrivalStationId;
    private Integer userId = null;

    private String name = null;
    private String surname = null;


    /**
     * <h2> Ticket with userID</h2>
     * @param id ticket id (PK)
     * @param rideId ride id (FK)
     * @param departureStationId departure station id (FK)
     * @param arrivalStationId arrival station id (FK)
     * @param userId user id (FK)
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Ticket(Integer id,
                  Integer rideId,
                  Integer departureStationId,
                  Integer arrivalStationId,
                  Integer userId) {
        super(id);
        this.rideId = rideId;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.userId = userId;
        createData();
        createStringData();
    }

    /**
     * <h2> Ticket with name and surname </h2>
     * @param id ticket id (PK)
     * @param rideId ride id (FK)
     * @param departureStationId departure station id (FK)
     * @param arrivalStationId arrival station id (FK)
     * @param name buyer's name
     * @param surname buyer's surname
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Ticket(Integer id,
                  Integer rideId,
                  Integer departureStationId,
                  Integer arrivalStationId,
                  String name,
                  String surname) {
        super(id);
        this.rideId = rideId;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.name = name;
        this.surname = surname;
        createData();
        createStringData();
    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("TICKET_ID", this.getID());
        dict.put("RIDE_ID", this.rideId);
        dict.put("ID_DEPARTURE_STATION", this.departureStationId);
        dict.put("ID_ARRIVAL_STATION", this.arrivalStationId);
        if (userId!=null) dict.put("USER_ID", this.userId);
        if (name!=null) dict.put("NAME", this.name);
        if (surname!=null) dict.put("SURNAME", this.surname);
        super.data = dict;
        super.table = "TICKETS";
    }

    private void createStringData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("TICKET_ID", this.getID());
        if (userId!=null) dict.put("USER_ID", this.userId);
        if (name!=null) dict.put("NAME", this.name);
        if (surname!=null) dict.put("SURNAME", this.surname);
        super.stringData = dict;
    }

    /**
     * <h2> Push object to Database (Table TICKETS)</h2>
     * Pushes created object to database with set attributes.
     * If no userId -> USER_ID null in database
     * etc.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        super.pushToDB();
    }

    /**
     * <h2> Set Name </h2>
     * Sets Ticket buyer's name
     * @param name buyer's name
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setName(String name) {

        this.name = name;
        createData();
        createStringData();
    }

    /**
     * <h2> Set Surname </h2>
     * Sets Ticket buyer's surname
     * @param surname buyer's surname
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setSurname(String surname) {

        this.surname = surname;
        createData();
        createStringData();
    }

    /**
     * <h2> Set User ID </h2>
     * Sets user id that buys the ticket (if buyer has an account)
     * @param userId buyer's user id
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
        createData();
        createStringData();
    }

    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public Integer getRideId() {
        return rideId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}