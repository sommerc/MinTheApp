package com.VBCPhDSymposium.MindTheApp;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    public void openPdfBooklet(View view)
    {
        Intent intent = new Intent(this, DisplayProgramPdf.class);
        intent.putExtra("kind", "VBCPhDSymposium_2016.pdf");
        startActivity(intent);
    }

    public void openPdfProgram(View view)
    {
        Intent intent = new Intent(this, DisplayProgramPdf.class);
        intent.putExtra("kind", "VBCPhDSymposium_2016_programme.pdf");
        startActivity(intent);
    }
}