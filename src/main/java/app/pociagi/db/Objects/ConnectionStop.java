package app.pociagi.db.Objects;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * <h2> Connection Stop </h2>
 * Representation of data from a row from STOPS database table.
 * Connection Stop objects provide information about by what Stations Connection is routed.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class ConnectionStop extends DBObject{
    private final Integer connectionId;
    private final Integer stationId;

    private final Time arrivalHour;
    private final Time departureHour;
    /**
     * <h2> Create Connection Stop object </h2>
     * Creates a connection stop object
     * @param connectionId connection id (PK/FK)
     * @param stationId station id (PK/FK)
     * @param arrivalHour time of arrival
     * @param departureHour time of departure
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public ConnectionStop(Integer connectionId, Integer stationId, Time arrivalHour, Time departureHour) {
        super();
        this.connectionId = connectionId;
        this.stationId = stationId;
        this.arrivalHour = arrivalHour;
        this.departureHour = departureHour;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public Time getArrivalHour() {
        return arrivalHour;
    }

    public Time getDepartureHour() {
        return departureHour;
    }

    /**
     * <h2> Push object to Database (Table STOPS)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", this.connectionId);
        dict.put("STATION_ID", this.stationId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = this.arrivalHour;
        dict.put("ARRIVAL_HOUR", dateFormat.format(date));
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date2 = this.departureHour;
        dict.put("DEPARTURE_HOUR", dateFormat.format(date2));
        super.data = dict;
        super.table = "STOPS";
        super.pushToDB();
    }
}

