package com.vbcphdsymposium.mindtheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vbcphdsymposium.mindtheapp.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayAbstracts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_abstract);

        List<SessionXmlParser.Entry> sessionEntries = SessionData.getInstance().getData();

        Intent intent = getIntent();
        int entry_id = intent.getIntExtra("entry_to_show", 0);

        SessionXmlParser.Entry entry_to_show = sessionEntries.get(0);
        int j = 0;
        for (int i=0; i<sessionEntries.size();i++) {
            if (sessionEntries.get(i).entryId == entry_id) {
                j=i;
                break;
            }
        }

        TextView summary = (TextView) findViewById(R.id.summary);
        Log.i("asdf", sessionEntries.get(j).summary);
        summary.setText(sessionEntries.get(j).summary);

    }
}