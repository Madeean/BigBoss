package umn.ac.bigboss.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static final String baseURL = "https://madeekan.madee.my.id/api/";
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){

        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
