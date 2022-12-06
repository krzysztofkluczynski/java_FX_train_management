package app.pociagi.db_classes_singletons;

public class User {
    private String userID;
    private String login;
    private String password;
    private String name;
    private String surname;

    private String email;



    public User() {}

    public User(String userID, String login, String password, String name, String surname, String email) {
        this.userID = userID;
        this.login = login;
        this.password = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() { return email; }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
