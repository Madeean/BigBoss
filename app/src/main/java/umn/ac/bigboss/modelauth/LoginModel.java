package umn.ac.bigboss.modelauth;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LoginModel {
    private DataLoginModel user;
    private String token;

    public DataLoginModel getUser() {
        return user;
    }

    public void setUser(DataLoginModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
