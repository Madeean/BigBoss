package umn.ac.bigboss.api;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import umn.ac.bigboss.modelauth.DataRequestPembayaranPengontrakModel;
import umn.ac.bigboss.modelauth.EditLogin;
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.modelauth.DataNamaKontrakanModel;
import umn.ac.bigboss.modelauth.PembayaranModel;

public interface ApiRequest {

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> ARLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<LoginModel> ARRegisterPemilik(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("rooms") int rooms,
            @Field("role") String role,
            @Field("nama_kontrakan") String nama_kontrakan
    );

    @FormUrlEncoded
    @POST("edit-profile-pengontrak")
    Call<EditLogin> AREditProfilePengontrak(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("umur") String umur
    );

    @GET("get-nama-kontrakan")
    Call<DataNamaKontrakanModel> ARNamaKontrakan();

    @Multipart
    @POST("register")
    Call<LoginModel> ARRegisterPengontrak(
            @Part MultipartBody.Part foto_muka,
            @Part ("name") RequestBody name,
            @Part ("email") RequestBody email,
            @Part ("password") RequestBody password,
            @Part ("umur") RequestBody umur,
            @Part ("role") RequestBody role,
            @Part ("alamat_sesuai_ktp") RequestBody alamat_sesuai_ktp,
            @Part ("alamat_kontrakan_sekarang") RequestBody alamat_kontrakan_sekarang,
            @Part ("harga_perbulan") RequestBody harga_perbulan,
            @Part ("nama_kontrakan") RequestBody nama_kontrakan

            );


    @GET("get-request-pembayaran")
    Call<DataRequestPembayaranPengontrakModel> ARListRequest(
            @Header("Authorization") String token
    );

    @GET("get-pembayaran-diterima-pengontrak")
    Call<DataRequestPembayaranPengontrakModel> ARHistoryPembayaran(
            @Header("Authorization") String token
    );

    @Multipart
    @POST("bayar")
    Call<PembayaranModel> ARBayar(
            @Header("Authorization") String token,
            @Part MultipartBody.Part bukti_bayar,
            @Part ("bulan") RequestBody bulan,
            @Part ("nama_pengontrak") RequestBody nama_pengontrak,
            @Part ("tanggal_bayar") RequestBody tanggal_bayar,
            @Part ("jumlah_bayar") RequestBody jumlah_bayar,
            @Part ("role") RequestBody role,
            @Part ("nama_kontrakan") RequestBody nama_kontrakan

    );

}
