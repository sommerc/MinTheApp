package com.vbcphdsymposium.mindtheapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
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

        TextView speaker_name = (TextView) findViewById(R.id.entry_speaker_name);
        speaker_name.setText(sessionEntries.get(j).presenterName);

        TextView title = (TextView) findViewById(R.id.entry_title);
        title.setText(sessionEntries.get(j).title);

        WebView summary = (WebView) findViewById(R.id.entry_summary);

        String summaryText = String.format("<html><body><p align=\"justify\">%s</p></body></html>", sessionEntries.get(j).summary);

        summary.loadData(summaryText, "text/html", "utf-8");

        ImageView speakerImg = (ImageView) findViewById(R.id.entry_speaker_img);
        Context context = speakerImg.getContext();
        int id = context.getResources().getIdentifier(sessionEntries.get(j).imageId, "drawable", context.getPackageName());

        speakerImg.setImageResource(id);
    }
}