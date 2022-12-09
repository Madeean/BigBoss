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

    private static final String FCMURL = "https://fcm.googleapis.com/fcm/";
    private static Retrofit retrofit;

    public static Retrofit konekFCM(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(FCMURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
