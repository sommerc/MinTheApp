package com.VBCPhDSymposium.MindTheApp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplaySession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_session);

        // get session data
        List<SessionXmlParser.Entry> sessionEntries = SessionData.getInstance().getData();

        // get session_id = which session to show
        Intent intent = getIntent();
        int session_id = intent.getIntExtra("session_to_show", 0);

        if (session_id == 5){
            // if it's 5 = panel discussion, start new PanelIntent
            Intent PanelIntent = new Intent(getApplicationContext(), DisplayPanel.class);
            startActivity(PanelIntent);
            finish();
        } else {
            // if it's a normal session, display this session
            String[] sessionColors = SessionData.getInstance().colors;

            // create list of sessions to show (all with the currrent session_id)
            ArrayList<SessionXmlParser.Entry> currentSessionEntries = new ArrayList<SessionXmlParser.Entry>();

            for (SessionXmlParser.Entry entry : sessionEntries) {
                if (entry.sessionId == session_id) {
                    currentSessionEntries.add(entry);
                }
            }

            // Set Activity bar title
            TextView sessionHeading = (TextView) findViewById(R.id.session_nr);
            sessionHeading.setText(String.format("Chair: %s", currentSessionEntries.get(0).chair));
            sessionHeading.setTextColor(Color.parseColor(sessionColors[session_id]));

            // Get SessionName
            String sessionName = SessionData.getInstance().getSessionName(session_id, true);

            // Make background color of action bar
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(sessionColors[session_id]));
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            setTitle(sessionName);

            // Create the adapter to convert the array to views
            SessionAdapter adapter = new SessionAdapter(this, currentSessionEntries);

            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.activity_display_session);

            // Cofigure it a bit
            listView.setDivider(new ColorDrawable(Color.parseColor(sessionColors[session_id])));
            listView.setDividerHeight(2);
            listView.setAdapter(adapter);

            // Create OnItemClickListener for clicks to view abstracts
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View v,
                                                                        int position, long id) {
                                                    Intent intent = new Intent(getApplicationContext(), DisplayAbstracts.class);

                                                    intent.putExtra("entry_to_show", ((SessionXmlParser.Entry) parent.getItemAtPosition(position)).entryId);
                                                    startActivity(intent);
                                                }
                                            }
            );
        }
    }

}

