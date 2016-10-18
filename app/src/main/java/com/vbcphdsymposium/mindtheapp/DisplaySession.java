package com.vbcphdsymposium.mindtheapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplaySession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_session);

        List<SessionXmlParser.Entry> sessionEntries = SessionData.getInstance().getData();

        Intent intent = getIntent();
        int session_id = intent.getIntExtra("session_to_show", 0);


        // Construct the data source
        ArrayList<SessionXmlParser.Entry> currentSessionEntries = new ArrayList<SessionXmlParser.Entry>();


        for (SessionXmlParser.Entry entry : sessionEntries) {
            if (entry.sessionId == session_id) {
                currentSessionEntries.add(entry);
            }

        }

        TextView session_nr = (TextView) findViewById(R.id.session_nr);
        session_nr.setText(String.format("Session %d", session_id +1));


        // Create the adapter to convert the array to views
        SessionAdapter adapter = new SessionAdapter(this, currentSessionEntries);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.activity_display_session);
        listView.setAdapter(adapter);

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
