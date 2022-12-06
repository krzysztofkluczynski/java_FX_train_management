package app.pociagi.utils;

import app.pociagi.db_classes_singletons.Ride;
import app.pociagi.db_classes_singletons.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppData {
    private static final AppData INSTANCE = new AppData();
    public User user;
    public Ride ride;
    public DatabaseHandler dbHandler;
    public Map<String, Integer> buyTicketData = new HashMap<String, Integer>();
    public ArrayList<Integer> pickedConnection;
    private AppData() {}

    public static AppData getInstance() {
        return INSTANCE;
    }
}
