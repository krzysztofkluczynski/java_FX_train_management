package app.pociagi;

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
