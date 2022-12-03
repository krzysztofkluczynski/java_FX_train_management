package app.pociagi;

public class ValidateSingletons {
    static RideSingleton ride = RideSingleton.getInstance();
    static UserSingleton user = UserSingleton.getInstance();

    public static boolean checkUser() {
        if(user.getUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkRide() {
        if(ride.getRide() == null) {
            return false;
        } else {
            return true;
        }
    }
}
