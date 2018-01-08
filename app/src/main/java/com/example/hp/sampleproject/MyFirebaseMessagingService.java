package com.example.hp.sampleproject;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by HP on 12/27/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String TAG="MTAG";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody()+remoteMessage.getNotification().getTitle());
        }
        String cut=remoteMessage.getNotification().getBody();

        String[] str=cut.split(" ");
        Movie movie=new Movie(0,str[0],str[1],str[2],str[3]);
        Intent intent=new Intent(this,RunningMovies.class);
        intent.putExtra("myname",str[0]);
        intent.putExtra("myname1",str[1]);
        intent.putExtra("myname2",str[2]);
        intent.putExtra("myname3",str[3]);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder notificationCompat=new NotificationCompat.Builder(this)
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentTitle(remoteMessage.getFrom())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logo);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        int notificationID=0;
        mNotificationManager.notify(notificationID, notificationCompat.build());
        SampleInterface send = GetRetrofit.getRetrofit().create(SampleInterface.class);
        final Call<Movie> regmovies = send.regmovies(new Movie(0,str[0],str[1],str[2],str[3]));
        regmovies.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.d(TAG, "onResponse: Hogya shyd"+response.message()+response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }

}
