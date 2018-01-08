package com.example.hp.sampleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Movie_Details extends AppCompatActivity {
    Button button;
    TextView textview2,textview3;
    TextView textview,textview4;
    int time1,time2;
    Date date,date2;
    String TAG = "MTAG";
    SimpleDateFormat sdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__details);

        Bundle a = getIntent().getExtras();
        ImageView imageView = (ImageView) findViewById(R.id.cover);
        imageView.setImageResource(R.drawable.cover);
        Picasso.with(getApplicationContext()).load(a.getString("image")).placeholder(R.drawable.omar).into(imageView);

        textview = (TextView) findViewById(R.id.name);
        textview2 = (TextView) findViewById(R.id.time);
        textview3 = (TextView) findViewById(R.id.textView3);
        textview4 = (TextView) findViewById(R.id.textView4);
        textview4.setText("Genre");
        textview.setText(a.getString("name"));
        textview2.setText(a.getString("time"));
        textview3.setText(a.getString("type"));



            DateFormat aFormatter = new SimpleDateFormat("HH:mm");
        Date dt = null;
        try {
            dt = aFormatter.parse(a.getString("time"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar aCalander = Calendar.getInstance();
            aCalander.setTime(dt);
            int hour = (aCalander.get(Calendar.HOUR)*60*60*1000);
            int minute = (aCalander.get(Calendar.MINUTE)*60*1000);
            time1 = hour+minute;

            DateFormat celltime = new SimpleDateFormat("HH:mm");
            Calendar calendar = Calendar.getInstance();
            String strDate = celltime.format(calendar.getTime());
        Date dt2 = null;
        try {
            dt2 = celltime.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(dt2);
            int hour2 = (calendar.get(Calendar.HOUR)*60*60*1000);
            int minute2 = (calendar.get(Calendar.MINUTE)*60*1000);
            time2 = hour2+minute2;
    }

    public void reserveTicket(View v) {
        button = (Button) findViewById(R.id.button3);
        if(time1 >= time2){
            button.setText("Reserved");
            Intent intent = new Intent(this, MyService.class);
            intent.putExtra("time", time1-time2);
            startService(intent);
        }
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusClass event) {
        button = (Button) findViewById(R.id.button3);
        button.setText("Reserve My Ticket");
    }
}