package com.vbcphdsymposium.mindtheapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sommerc on 10/14/2016.
 */

final class MainAdapter extends android.widget.BaseAdapter {
    private final List<SessionItem> mItems = new ArrayList<SessionItem>();
    private final LayoutInflater mInflater;

    public MainAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        SessionData sd = SessionData.getInstance();
        mItems.add(new SessionItem(sd.getSessionName(0, true), R.drawable.session1));
        mItems.add(new SessionItem(sd.getSessionName(1, true), R.drawable.session2));
        mItems.add(new SessionItem(sd.getSessionName(2, true), R.drawable.session3));
        mItems.add(new SessionItem(sd.getSessionName(3, true), R.drawable.session4));
        mItems.add(new SessionItem(sd.getSessionName(4, true), R.drawable.session5));

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public SessionItem getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        SessionItem item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private static class SessionItem {
        public final String name;
        public final int drawableId;

        SessionItem(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}