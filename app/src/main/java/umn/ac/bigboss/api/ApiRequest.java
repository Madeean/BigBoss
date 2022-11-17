package umn.ac.bigboss.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
}
