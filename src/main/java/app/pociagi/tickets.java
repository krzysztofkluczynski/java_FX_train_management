package app.pociagi;

public class tickets {
     public static void buy_ticket (int ride_id, int begin_station_id, int end_station_id,
                                   String user_id, String name, String surname) {
        String sql_query;
        if ((user_id == null && (name == null || surname == null)) ||
        user_id != null && (name != null || surname != null) ||
                (name == null && surname != null) || (name != null && surname == null)) {
            throw new java.lang.Error("Wrong arguments!");
        }
        else if (user_id == null) {
            sql_query = String.format("INSERT INTO tickets VALUES (default, %s, %s, %s, Null, '%s', '%s')",
                    ride_id, begin_station_id, end_station_id, name, surname);
        }
        else {
            sql_query = String.format(
                    "INSERT INTO tickets VALUES (default, %s, %s, %s, %s, Null, Null)",
                    ride_id, begin_station_id, end_station_id, user_id);
        }
        //sql_query = "INSERT INTO tickets VALUES (default, 1, 6, 9, 18, Null, Null);";
        DatabaseHandler handle = DatabaseHandler.getInstance();
        handle.insertData(sql_query);
    }
}
