package com.vbcphdsymposium.mindtheapp;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        MainAdapter adapter = new MainAdapter(this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DisplaySession.class);
                intent.putExtra("session_to_show", position);
                startActivity(intent);
            }
        }
        );

        loadSessionData();
    }

    private void loadSessionData() {
        Resources res = this.getResources();
        XmlResourceParser xrp = res.getXml(R.xml.program);

        List<SessionXmlParser.Entry> sessionEntries = null;
        SessionXmlParser sxp = new SessionXmlParser();
        try {
            sessionEntries = sxp.parse(xrp);
        }
        catch (IOException | XmlPullParserException e) {
//            Log.i("MainActivity", "IO Error");
        }
        SessionData.getInstance().setData(sessionEntries);

    }

    public void openPdf(View view)
    {
        Intent intent = new Intent(this, DisplayProgramPdf.class);
        startActivity(intent);
    }
}