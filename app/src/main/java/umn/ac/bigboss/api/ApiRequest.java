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
import umn.ac.bigboss.modelauth.LoginModel;
import umn.ac.bigboss.modelauth.DataNamaKontrakanModel;

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

}
