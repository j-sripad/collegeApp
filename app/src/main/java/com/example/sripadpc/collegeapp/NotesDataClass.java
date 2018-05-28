package com.example.sripadpc.collegeapp;

/**
 * Created by sripadpc on 27-05-2018.
 */

public class NotesDataClass {
    String name;
    String date;

    public NotesDataClass(String name, String date, String url) {
        this.name = name;
        this.date = date;
        Url = url;
    }

    public String getDate() {
        return date;
    }

    String Url;
    NotesDataClass()
    {}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }*/
}
