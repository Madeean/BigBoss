package umn.ac.bigboss.modelauth;

import java.util.List;

public class GetUserKontrakanModel {
    private String message;
    private String status;
    private List<DataLoginModel> user;

    public List<DataLoginModel> getUser() {
        return user;
    }

    public void setUser(List<DataLoginModel> user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
