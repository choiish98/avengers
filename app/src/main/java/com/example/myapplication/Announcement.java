package com.example.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class Announcement {
    private String title;
    private String name;
    private String serialNum;
    private String tag;
    private int recruitment;
    private String contents;

    Announcement(JSONObject o) {
        try {
            this.title = o.getString("title");
            this.name = o.getString("name");
            this.serialNum = o.getString("serialNum");
            this.tag = o.getString("tag");
            this.recruitment = o.getInt("recruitment");
            this.contents = o.getString("contents");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getRecruitment() {
        return recruitment;
    }

    public String getContents() {
        return contents;
    }

    public String getName() {
        return name;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }
}
