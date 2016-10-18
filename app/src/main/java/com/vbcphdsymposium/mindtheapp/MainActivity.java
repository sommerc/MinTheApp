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
import android.widget.RelativeLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        MyAdapter adapter = new MyAdapter(this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Log.i("MainActivity LOG", String.format("%d %d ", position, id) );
                Intent intent = new Intent(getApplicationContext(), DisplaySession.class);


                intent.putExtra("session_to_show", position);
                startActivity(intent);

            }
        }
        );

        Resources res = this.getResources();
        XmlResourceParser xrp = res.getXml(R.xml.program);

        SessionXmlParser sxp = new SessionXmlParser();

        List<SessionXmlParser.Entry> sessionEntries = null;
        try {
             sessionEntries = sxp.parse(xrp);
        }
        catch (IOException e) {
            Log.i("MainActivity", "IO Error");
        } catch (XmlPullParserException e) {
            Log.i("MainActivity", "IO Error");
        }

        Log.i("resssasdfasdf", String.format("%d", sessionEntries.size()));

        SessionData.getInstance().setData(sessionEntries);
        List<SessionXmlParser.Entry> data = SessionData.getInstance().getData();

        Log.i("ress   sasdfasdf", String.format("%d", data.size()));


    }
}