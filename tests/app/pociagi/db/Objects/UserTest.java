package app.pociagi.db.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void setEmail() {
        User usr = new User(1, "aa", "ba", "cd", "dd");
        usr.setEmail("aaa");
        assertEquals("aaa", usr.getEmail());
    }

    @Test
    void getSurname() {
        User usr = new User(1, "aa", "ba", "cd", "dd");
        assertEquals("dd", usr.getSurname());
    }

    @Test
    void getEmail() {
        User usr = new User(1, "aa", "ba", "cd", "dd");
        assertEquals("dd", usr.getSurname());
    }

    @Test
    void getLogin() {
        User usr = new User(1, "aa", "ba", "cd", "dd");
        assertEquals("dd", usr.getSurname());
    }

    @Test
    void getName() {
        User usr = new User(1, "aa", "ba", "cd", "dd");
        assertEquals("dd", usr.getSurname());
    }

    @Test
    void getPassword() {
        User usr = new User(1, "aa", "ba", "cd", "dd");
        assertEquals("dd", usr.getSurname());
    }
}