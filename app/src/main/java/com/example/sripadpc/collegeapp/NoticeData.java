package com.example.sripadpc.collegeapp;

/**
 * Created by sripadpc on 27-05-2018.
 */

public class NoticeData {
    private String Name;
private String date;
    private String URL;
    NoticeData(){}

    public String getDate() {
        return date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public NoticeData(String Name, String date, String URL) {
        this.Name = Name;
        this.date = date;

        this.URL = URL;
    }


}
