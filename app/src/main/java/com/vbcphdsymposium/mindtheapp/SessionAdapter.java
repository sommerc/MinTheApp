package com.VBCPhDSymposium.MindTheApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sommerc on 10/18/2016.
 */

public class SessionAdapter extends ArrayAdapter<SessionXmlParser.Entry> {
    public SessionAdapter(Context context, ArrayList<SessionXmlParser.Entry> enries) {
        super(context, 0, enries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SessionXmlParser.Entry entry = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session_item, parent, false);
        }

        // Lookup view for data population
        TextView speakerName = (TextView) convertView.findViewById(R.id.speaker_name);
        TextView speakerAffi = (TextView) convertView.findViewById(R.id.speaker_affi);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView speakerTime = (TextView) convertView.findViewById(R.id.speaker_time);
        ImageView speakerImg = (ImageView) convertView.findViewById(R.id.speaker_img);

        // Populate the data into the template view using the data object
        speakerAffi.setText(String.format("%s, %s", entry.department, entry.affiliation));

        // create display name
        String name = entry.presenterName;

        // check if keynote
        if (entry.keyNote==1) {name+=" (Keynote)";}
        if (entry.addedDim==1) {name+=" (Added Dimension)";}

        // set it
        speakerName.setText(name);
        speakerTime.setText(String.format("%s %s", entry.day, entry.time));

        // lookup drawable id for speaker image from session data
        Context context = speakerImg.getContext();
        int id = context.getResources().getIdentifier(entry.imageId, "drawable", context.getPackageName());

        // then display it
        speakerImg.setImageResource(id);
        title.setText(entry.title);

        // Return the completed view to render on screen
        return convertView;
    }
}
