package model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class User {

    private static int id;
    private String login;
    private String password;
    private int user_id;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.user_id=id;
        id++;
    }
}
