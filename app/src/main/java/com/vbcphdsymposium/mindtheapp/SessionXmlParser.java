package com.VBCPhDSymposium.MindTheApp;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sommerc on 10/18/2016.
 */

public class SessionXmlParser {
    private static final String ns = null;

    public List<Entry> parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Entry> entries = new ArrayList<Entry>();

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("entry")) {
                entries.add(readEntry(parser));
            }
        }
        return entries;
    }

    public static class Entry {
        public final int entryId;
        public final int sessionId;
        public final String sessionName;
        public final String department;
        public final String presenterName;
        public final String affiliation;
        public final String imageId;
        public final String title;
        public final String date;
        public final String day;
        public final String time;
        public final String chair;

        public final int keyNote;
        public final int addedDim;

        public final String summary;

        private Entry(int entryId, int sessionId, String sessionName, String department,
                      String presenterName, String affiliation, String imageId, String title,
                      String date, String day, String time, int keyNote, int addedDim, String summary,
                      String chair)

        {
            this.entryId = entryId;
            this.sessionId = sessionId;
            this.sessionName = sessionName;
            this.department = department;
            this.presenterName = presenterName;
            this.affiliation = affiliation;
            this.imageId = imageId;
            this.title = title;
            this.date = date;
            this.day = day;
            this.time = time;
            this.keyNote = keyNote;
            this.addedDim = addedDim;
            this.summary = summary;
            this.chair = chair;

        }
    }

    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        int entryId = 0;
        int sessionId = 0;
        String sessionName = null;
        String department = null;
        String presenterName = null;
        String affiliation = null;
        String imageId = null;
        String title = null;
        String date = null;
        String day = null;
        String time = null;
        int keyNote = 0;
        int addedDim = 0;
        String summary = null;
        String chair = "";

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("entryId")) {
                entryId = readInt(parser, "entryId");}
            else if (name.equals("sessionId")) {
                sessionId = readInt(parser, "sessionId");
            } else if (name.equals("sessionName")) {
                sessionName = readString(parser, "sessionName");
            } else if (name.equals("department")) {
                department = readString(parser, "department");
            } else if (name.equals("presenterName")) {
                presenterName = readString(parser, "presenterName");
            } else if (name.equals("affiliation")) {
                affiliation = readString(parser, "affiliation");
            } else if (name.equals("imageId")) {
                imageId = readString(parser, "imageId");
            } else if (name.equals("title")) {
                title = readString(parser, "title");
            } else if (name.equals("date")) {
                date = readString(parser, "date");
            } else if (name.equals("day")) {
                day = readString(parser, "day");
            } else if (name.equals("time")) {
                time = readString(parser, "time");
            } else if (name.equals("keyNote")) {
                keyNote = readInt(parser, "keyNote");
            } else if (name.equals("addedDim")) {
                addedDim = readInt(parser, "addedDim");
            } else if (name.equals("abstract")) {
                summary = readString(parser, "abstract");
            } else if (name.equals("chair")) {
                chair = readString(parser, "chair");
            }
            else {
                skip(parser);
            }
        }
        return new Entry(entryId, sessionId, sessionName, department, presenterName, affiliation, imageId, title,
                date, day, time, keyNote, addedDim, summary, chair);
    }

    // Processes title tags in the feed.
    private String readString(XmlPullParser parser, String entry) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, entry);
        String res = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, entry);
        return res;
    }

    // Processes title tags in the feed.
    private int readInt(XmlPullParser parser, String entry) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, entry);
        int res = readInt(parser);
        parser.require(XmlPullParser.END_TAG, ns, entry);
        return res;
    }


    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private int readInt(XmlPullParser parser) throws IOException, XmlPullParserException {
        String res = this.readText(parser);
        return Integer.parseInt(res);
    }

    // Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
    // if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
    // finds the matching END_TAG (as indicated by the value of "depth" being 0).
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}