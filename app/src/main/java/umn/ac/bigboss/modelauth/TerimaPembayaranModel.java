package umn.ac.bigboss.modelauth;

public class TerimaPembayaranModel {
    private String message;
    private PembayaranTanpaUserModel data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PembayaranTanpaUserModel getData() {
        return data;
    }

    public void setData(PembayaranTanpaUserModel data) {
        this.data = data;
    }
}
