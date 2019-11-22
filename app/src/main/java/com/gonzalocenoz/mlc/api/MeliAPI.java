package com.gonzalocenoz.mlc.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeliAPI {

    private static IMeliAPI api;
    private static final String BASE_URL = "https://api.mercadolibre.com/";

    public static IMeliAPI getMeliAPI()
    {
        if(api==null)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();


            Retrofit r = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = r.create(IMeliAPI.class);
        }

        return api;
    }
}