package app.pociagi.db.Utils;

import app.pociagi.db.Objects.Station;
import app.pociagi.db.Objects.User;

import java.sql.SQLException;

public class FindUser{
    public static User findByLogin(String login) {
        try {
            Integer user_id = Integer.parseInt(Finder.find("USERS", "USER_ID", "LOGIN", login));
            String password = Finder.find("USERS", "PASSWORD", "LOGIN", login);
            String name = Finder.find("USERS", "NAME", "LOGIN", login);
            String surname = Finder.find("USERS", "SURNAME", "LOGIN", login);
            String email = Finder.find("USERS", "EMAIL", "LOGIN", login);

            return new User(user_id, login, password, name, surname, email);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}