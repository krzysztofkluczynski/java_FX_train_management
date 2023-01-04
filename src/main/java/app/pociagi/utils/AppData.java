package app.pociagi.utils;

import app.pociagi.db.Objects.*;
import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.db.Utils.Route;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppData {
    private static final AppData INSTANCE = new AppData();
    public User user = null;

    public Date pickedDate = null;

    public ArrayList<Connection> pickedConnections;

    public ArrayList<Ticket> buyTicketData;

    public DBObject selectedObject = null;
    public Route pickedRoute;

    public Station from = null;
    public Station destination = null;
    private AppData() {}

    public static AppData getInstance() {
        return INSTANCE;
    }
}
