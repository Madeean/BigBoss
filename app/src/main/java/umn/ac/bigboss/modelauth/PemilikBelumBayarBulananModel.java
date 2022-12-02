package umn.ac.bigboss.modelauth;

import java.util.ArrayList;
import java.util.List;

public class PemilikBelumBayarBulananModel {
    private List<Integer> list_data_sudah_bayar;
    private List<DataYangSudahBayarModel> data_yang_sudah_bayar;
    private List<DataLoginModel> data_yang_belum_bayar;
    private String message;
    private List<DataLoginModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataLoginModel> getData() {
        return data;
    }

    public void setData(List<DataLoginModel> data) {
        this.data = data;
    }

    public List<Integer> getList_data_sudah_bayar() {
        return list_data_sudah_bayar;
    }

    public void setList_data_sudah_bayar(List<Integer> list_data_sudah_bayar) {
        this.list_data_sudah_bayar = list_data_sudah_bayar;
    }

    public List<DataYangSudahBayarModel> getData_yang_sudah_bayar() {
        return data_yang_sudah_bayar;
    }

    public void setData_yang_sudah_bayar(List<DataYangSudahBayarModel> data_yang_sudah_bayar) {
        this.data_yang_sudah_bayar = data_yang_sudah_bayar;
    }

    public List<DataLoginModel> getData_yang_belum_bayar() {
        return data_yang_belum_bayar;
    }

    public void setData_yang_belum_bayar(List<DataLoginModel> data_yang_belum_bayar) {
        this.data_yang_belum_bayar = data_yang_belum_bayar;
    }
}
