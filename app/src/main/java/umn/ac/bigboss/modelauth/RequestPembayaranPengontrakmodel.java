package umn.ac.bigboss.modelauth;

import java.util.List;

public class RequestPembayaranPengontrakmodel {
    private int id;
    private int user_id;
    private int bulan;
    private String nama_pengontrak;
    private String nama_kontrakan;
    private String status_lunas;
    private String status_konfirmasi;
    private String tanggal_bayar;
    private String bukti_bayar;
    private int jumlah_bayar;
    private List<DataLoginModel> user;

    public List<DataLoginModel> getUser() {
        return user;
    }

    public void setUser(List<DataLoginModel> user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public String getNama_pengontrak() {
        return nama_pengontrak;
    }

    public void setNama_pengontrak(String nama_pengontrak) {
        this.nama_pengontrak = nama_pengontrak;
    }

    public String getNama_kontrakan() {
        return nama_kontrakan;
    }

    public void setNama_kontrakan(String nama_kontrakan) {
        this.nama_kontrakan = nama_kontrakan;
    }

    public String getStatus_lunas() {
        return status_lunas;
    }

    public void setStatus_lunas(String status_lunas) {
        this.status_lunas = status_lunas;
    }

    public String getStatus_konfirmasi() {
        return status_konfirmasi;
    }

    public void setStatus_konfirmasi(String status_konfirmasi) {
        this.status_konfirmasi = status_konfirmasi;
    }

    public String getTanggal_bayar() {
        return tanggal_bayar;
    }

    public void setTanggal_bayar(String tanggal_bayar) {
        this.tanggal_bayar = tanggal_bayar;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
    }

    public void setBukti_bayar(String bukti_bayar) {
        this.bukti_bayar = bukti_bayar;
    }

    public int getJumlah_bayar() {
        return jumlah_bayar;
    }

    public void setJumlah_bayar(int jumlah_bayar) {
        this.jumlah_bayar = jumlah_bayar;
    }
}
