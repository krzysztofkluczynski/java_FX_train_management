package app.pociagi;

import java.util.ArrayList;

public class AppData {
    private static final AppData INSTANCE = new AppData();
    public User user;
    public Ride ride;
    public DatabaseHandler dbHandler;

    public ArrayList<Integer> pickedRide;
    private AppData() {}

    public static AppData getInstance() {
        return INSTANCE;
    }
}
