package com.vbcphdsymposium.mindtheapp;

import java.util.List;

/**
 * Created by sommerc on 10/18/2016.
 */

public class SessionData {
    private List<SessionXmlParser.Entry> data;
    public List<SessionXmlParser.Entry> getData() {return data;}
    public void setData(List<SessionXmlParser.Entry> data) {this.data = data;}

    private final String[] names = {"Molecular Toolbox", "Manipulating the Code", "Bioengineering Medicine",
                                    "Shaping Ecosystems", "Added Dimension & Panel Discussion"};
    public String getSessionName(int sessionId, boolean withPrefix) {
        String name = names[sessionId];
        if (withPrefix) {
            String prefix = "";
            if (sessionId==0) {
                prefix = "Session I:";
            } else if (sessionId==1) {
                prefix = "Session II:";
            } else if (sessionId==2) {
                prefix = "Session III:";
            } else if (sessionId==3) {
                prefix = "Session IV:";
            } else if (sessionId==4) {
                prefix = "";
            }
            return String.format("%s %s", prefix, name);
        }
        return name;
    }

    private static final SessionData holder = new SessionData();
    public static SessionData getInstance() {return holder;}
}
