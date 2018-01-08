package com.example.hp.sampleproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by HP on 11/22/2017.
 */

public interface SampleInterface {

    @GET("movie")
    Call<List<Movie>>getmovies();

    @POST("m")
    Call<User> sendmovies(@Body User user);

    @GET("m")
    Call<List<User>> getusers();

    @POST("movie")
    Call<Movie> regmovies(@Body Movie movie);
}
