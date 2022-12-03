package app.pociagi;

public class RideSingleton {
    private static final RideSingleton INSTANCE = new RideSingleton();
    private Ride ride;
    private RideSingleton() {}

    public static RideSingleton getInstance() {
        return INSTANCE;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }


}
