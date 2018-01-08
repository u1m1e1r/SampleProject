package com.example.hp.sampleproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 12/27/2017.
 */

public class GetRetrofit {
    private static Retrofit retrofit;
   public static Retrofit getRetrofit()
    {   if(retrofit==null) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/laravelfri/public/api/")
                .addConverterFactory(GsonConverterFactory.create());

         retrofit = builder.build();
    }
    return retrofit;
    }
}
