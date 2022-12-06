package app.pociagi.db_classes_singletons;

public class Seat {
    public Integer sit_number;
    public Integer car_number;
    public Integer seat_id;

    public Seat(Integer c, Integer s, Integer id) {
        sit_number = s;
        car_number = c;
        seat_id = id;
    }
}
