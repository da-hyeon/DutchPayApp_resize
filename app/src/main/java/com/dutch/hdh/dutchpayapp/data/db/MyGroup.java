package com.dutch.hdh.dutchpayapp.data.db;

public class MyGroup {
    private String groupID;
    private String participants;
    private String groupName;

    public MyGroup(String groupID, String participants, String groupName) {
        this.groupID = groupID;
        this.participants = participants;
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
