package com.example.hp.sampleproject;

import android.content.Context;
import android.content.Intent;

/**
 * Created by HP on 12/28/2017.
 */

public class SwitchEvent {
    Context c;
    Intent i;
    public SwitchEvent(Intent i, Context c){
        this.c = c;
        this.i = i;
    }
}
