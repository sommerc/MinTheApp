package com.VBCPhDSymposium.MindTheApp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sommerc on 10/25/2016.
 */

public class DisplayPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_discussion);

        String[] sessionColors = SessionData.getInstance().colors;

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(sessionColors[5]));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        String sessionName = SessionData.getInstance().getSessionName(5, true);
        setTitle(sessionName);
    }
}