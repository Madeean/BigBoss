package umn.ac.bigboss.modelauth;

import java.util.List;

public class ValidationBEAuth {
    private List<String> email;
    private List<String> password;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }
}
