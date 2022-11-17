package umn.ac.bigboss.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import umn.ac.bigboss.modelauth.DataLoginModel;
import umn.ac.bigboss.modelauth.LoginModel;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> ARLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
