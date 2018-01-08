package com.example.hp.sampleproject;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class MyService extends Service {
    String TAG = "MTAG";
    Handler handler;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle a = intent.getExtras();
        int b = a.getInt("time");
        Log.d(TAG, "onStartCommand: " + b);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(getBaseContext())
                        .setContentText("Its Movie time")
                        .setContentTitle("Movie")
                        .setSmallIcon(R.drawable.logo);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


                int notificationID = 0;
                mNotificationManager.notify(notificationID, notificationCompat.build());
                SampleInterface send = GetRetrofit.getRetrofit().create(SampleInterface.class);

            }
        }, b);

        return Service.START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
