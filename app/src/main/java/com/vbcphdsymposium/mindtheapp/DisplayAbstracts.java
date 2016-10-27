package com.VBCPhDSymposium.MindTheApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

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

        String[] sessionColors = SessionData.getInstance().colors;


        TextView speaker_name = (TextView) findViewById(R.id.entry_speaker_name);
        speaker_name.setText(sessionEntries.get(j).presenterName);

        TextView speaker_affi = (TextView) findViewById(R.id.entry_speaker_affi);
        speaker_affi.setText(String.format("%s, %s", sessionEntries.get(j).department, sessionEntries.get(j).affiliation));

        if (sessionEntries.get(j).addedDim == 1) {
            TextView speaker_mod = (TextView) findViewById(R.id.entry_speaker_mod);
            speaker_mod.setText("ADDED DIMENSION");
            speaker_mod.setTextColor(Color.parseColor(sessionColors[4]));
            speaker_mod.setTextColor(speaker_mod.getTextColors().withAlpha(80));


        } else if (sessionEntries.get(j).keyNote == 1) {
            TextView speaker_mod = (TextView) findViewById(R.id.entry_speaker_mod);
            speaker_mod.setText("KEYNOTE LECTURE");
            speaker_mod.setTextColor(Color.parseColor(sessionColors[sessionEntries.get(j).sessionId]));
            speaker_mod.setTextColor(speaker_mod.getTextColors().withAlpha(80));
        }

        View stripe = (View) findViewById(R.id.entry_stripe);
        ColorDrawable colorDrawable = new ColorDrawable();
        stripe.setBackgroundColor(Color.parseColor(sessionColors[sessionEntries.get(j).sessionId]));

        TextView title = (TextView) findViewById(R.id.entry_title);
        title.setText(sessionEntries.get(j).title);

        WebView summary = (WebView) findViewById(R.id.entry_summary);
        summary.setBackgroundColor(Color.TRANSPARENT);

        String summaryText = String.format("<html><body><p align=\"justify\">%s</p></body></html>", sessionEntries.get(j).summary);

        summary.loadData(summaryText, "text/html", "utf-8");

        ImageView speakerImg = (ImageView) findViewById(R.id.entry_speaker_img);
        Context context = speakerImg.getContext();
        int id = context.getResources().getIdentifier(sessionEntries.get(j).imageId, "drawable", context.getPackageName());

        speakerImg.setImageResource(id);

        ColorDrawable colorDrawable2 = new ColorDrawable(Color.parseColor(sessionColors[sessionEntries.get(j).sessionId]));
        getSupportActionBar().setBackgroundDrawable(colorDrawable2);

        String sessionName = SessionData.getInstance().getSessionName(sessionEntries.get(j).sessionId, true);
        setTitle(sessionName);
    }
}