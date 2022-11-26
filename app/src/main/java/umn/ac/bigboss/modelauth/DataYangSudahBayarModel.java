package umn.ac.bigboss.modelauth;

public class DataYangSudahBayarModel {
    private int user_id;
    private String nama_pengontrak;
    private String bukti_bayar;
    private String nama_kontrakan;
    private String status_konfirmasi;
    private String status_lunas;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNama_pengontrak() {
        return nama_pengontrak;
    }

    public void setNama_pengontrak(String nama_pengontrak) {
        this.nama_pengontrak = nama_pengontrak;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
    }

    public void setBukti_bayar(String bukti_bayar) {
        this.bukti_bayar = bukti_bayar;
    }

    public String getNama_kontrakan() {
        return nama_kontrakan;
    }

    public void setNama_kontrakan(String nama_kontrakan) {
        this.nama_kontrakan = nama_kontrakan;
    }

    public String getStatus_konfirmasi() {
        return status_konfirmasi;
    }

    public void setStatus_konfirmasi(String status_konfirmasi) {
        this.status_konfirmasi = status_konfirmasi;
    }

    public String getStatus_lunas() {
        return status_lunas;
    }

    public void setStatus_lunas(String status_lunas) {
        this.status_lunas = status_lunas;
    }
}
