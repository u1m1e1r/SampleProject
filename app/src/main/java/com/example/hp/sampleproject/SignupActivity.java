package com.example.hp.sampleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    private EditText name,mail,pass;
    private Integer token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    void create(View view){
        Random random = new Random();
        token=random.nextInt(4000);
        name = (EditText) findViewById(R.id.input_name);
        mail = (EditText) findViewById(R.id.input_email);
        pass = (EditText) findViewById(R.id.input_password);
        User user = new User(name.getText().toString(),mail.getText().toString(),pass.getText().toString(),token);


        SampleInterface send = GetRetrofit.getRetrofit().create(SampleInterface.class);
        Call<User> call = send.sendmovies(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),RunningMovies.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"User already exist's",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}
