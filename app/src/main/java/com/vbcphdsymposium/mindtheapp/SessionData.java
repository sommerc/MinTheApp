package com.vbcphdsymposium.mindtheapp;

import java.util.List;

/**
 * Created by sommerc on 10/18/2016.
 */

public class SessionData {
    private List<SessionXmlParser.Entry> data;
    public List<SessionXmlParser.Entry> getData() {return data;}
    public void setData(List<SessionXmlParser.Entry> data) {this.data = data;}

    private static final SessionData holder = new SessionData();
    public static SessionData getInstance() {return holder;}
}
