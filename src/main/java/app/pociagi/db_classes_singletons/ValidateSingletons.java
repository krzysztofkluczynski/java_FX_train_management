package app.pociagi.db_classes_singletons;

import app.pociagi.utils.AppData;

public class ValidateSingletons {
    static AppData appdata = AppData.getInstance();

    public static boolean checkUser() {
        if(appdata.user == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkRide() {
        if(appdata.ride == null) {
            return false;
        } else {
            return true;
        }
    }
}
