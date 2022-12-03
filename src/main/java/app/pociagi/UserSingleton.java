package app.pociagi;

public class UserSingleton {
    private static final UserSingleton INSTANCE = new UserSingleton();
    private User user;
    private UserSingleton() {}

    public static UserSingleton getInstance() {
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
