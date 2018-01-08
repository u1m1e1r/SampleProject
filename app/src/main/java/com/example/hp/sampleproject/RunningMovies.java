package com.example.hp.sampleproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RunningMovies extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static List<Movie> movies;
    public static CustomAdapter customAdapter;

    public void logout(View view) {
        sharedPreferences.edit().clear().apply();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_movies);
        sharedPreferences = getSharedPreferences("file", MODE_PRIVATE);
        final ListView listview = (ListView) findViewById(R.id.movies_list);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/laravelfri/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SampleInterface sampleInterface = retrofit.create(SampleInterface.class);

        Call<List<Movie>> myModelCall = sampleInterface.getmovies();

        myModelCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movies = response.body();
                customAdapter = new CustomAdapter(getApplicationContext(), 0, movies);
                listview.setAdapter(customAdapter);
                listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Movie m = (Movie) listview.getAdapter().getItem(position);
                        Intent intent = new Intent(getApplicationContext(), Movie_Details.class);
                        intent.putExtra("id", m.getId());
                        intent.putExtra("name", m.getMovie_name());
                        intent.putExtra("time", m.getTime());
                        intent.putExtra("image", m.getImage());
                        intent.putExtra("type", m.getDescription());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });



    }
}
