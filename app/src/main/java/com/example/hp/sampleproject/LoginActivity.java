package com.example.hp.sampleproject;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText mail, pass;
    String TAG = "MTAG";

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SwitchEvent event) {
        Intent intent = event.i;
        Context context = event.c;
        Button button = (Button) findViewById(R.id.btn_login);
        if (intent.getAction().equals("android.intent.action.AIRPLANE_MODE")) {

            boolean a = intent.getBooleanExtra("state", false);
                if(a == false){
          button.setEnabled(true);
            }else if(a == true){
                button.setEnabled(false);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail = (EditText) findViewById(R.id.input_mail);
        sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        mail.setText(sp.getString("key", ""));
        if (sp.getString("user", "").equals("")) {

        } else {
            Intent i = (new Intent(getApplicationContext(), RunningMovies.class)).putExtra("user", sp.getString("user", ""));
            startActivity(i);
        }
    }

    void register(View view) {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }

    void check_data(View view) {
        mail = (EditText) findViewById(R.id.input_mail);
        pass = (EditText) findViewById(R.id.input_pass);

        sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("key", mail.getText().toString());
        edit.commit();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/laravelfri/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SampleInterface sampleInterface = retrofit.create(SampleInterface.class);
        final Call<List<User>> myModelCall = sampleInterface.getusers();

        myModelCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> sh = response.body();

                for (User s : sh) {
                    if (s.getEmail().equals(mail.getText().toString())) {
                        if (s.getPassword().equals(pass.getText().toString())) {
                            sp.edit().putString("user", s.getName()).apply();
                            Toast.makeText(getApplicationContext(), "welcome" + s.getName(), Toast.LENGTH_SHORT).show();
                            Intent i = (new Intent(getApplicationContext(), RunningMovies.class)).putExtra("user", s.getName());
                            startActivity(i);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
