package com.VBCPhDSymposium.MindTheApp;

import java.util.List;

/**
 * Created by sommerc on 10/18/2016.
 */

/*
* Singleton to hold session data, colors and names
* */
public class SessionData {
    private List<SessionXmlParser.Entry> data;
    public List<SessionXmlParser.Entry> getData() {return data;}
    public void setData(List<SessionXmlParser.Entry> data) {this.data = data;}

    private final String[] names = {"Molecular Toolbox", "Manipulating the Code", "Bioengineering Medicine",
                                    "Shaping Ecosystems", "Added Dimension", "Panel Discussion"};
    public final String[] colors = {"#662d8d","#16397f","#ee602d","#006338","#A12367", "#007B92"};


    public String getSessionTitle(int sessionId) {
        String prefix = "";
        if (sessionId==0) {
            prefix = "Session I: ";
        } else if (sessionId==1) {
            prefix = "Session II: ";
        } else if (sessionId==2) {
            prefix = "Session III: ";
        } else if (sessionId==3) {
            prefix = "Session IV: ";
        } else if (sessionId==4) {
            prefix = names[sessionId];
        }
        else if (sessionId==5) {
            prefix = names[sessionId];
        }
        return prefix;
    }

    public String getSessionName(int sessionId, boolean withPrefix) {
        String name = names[sessionId];
        if (sessionId>3) {return name;}
        if (withPrefix) {
            String prefix = getSessionTitle(sessionId);

            return String.format("%s%s", prefix, name);
        }
        return name;
    }

    private static final SessionData holder = new SessionData();
    public static SessionData getInstance() {return holder;}
}
