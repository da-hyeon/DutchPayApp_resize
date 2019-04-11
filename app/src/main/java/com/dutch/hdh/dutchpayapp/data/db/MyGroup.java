package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyGroup {
    @SerializedName("grouplist")
    private ArrayList<GroupList> groupLists;

    public ArrayList<GroupList> getGroupLists() {
        return groupLists;
    }

    public void setGroupLists(ArrayList<GroupList> groupLists) {
        this.groupLists = groupLists;
    }
}