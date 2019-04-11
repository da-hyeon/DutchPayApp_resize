package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventList {

    @SerializedName("eventlist")
    private ArrayList<Event> eventList;

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }
}
