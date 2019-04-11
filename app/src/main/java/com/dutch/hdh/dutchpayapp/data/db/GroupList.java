package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class GroupList {
    @SerializedName("group_Acode")
    private String groupID;

    @SerializedName("group_Aname")
    private String groupName;

    @SerializedName("user_Code")
    private String userCode;

    @SerializedName("start_Date")
    private String groupCreationDate;

    @SerializedName("group_content_from_json")
    private String group_content_from_json;

    @SerializedName("people_number")
    private int participantsCount;


    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getGroupCreationDate() {
        return groupCreationDate;
    }

    public void setGroupCreationDate(String groupCreationDate) {
        this.groupCreationDate = groupCreationDate;
    }

    public String getGroup_content_from_json() {
        return group_content_from_json;
    }

    public void setGroup_content_from_json(String group_content_from_json) {
        this.group_content_from_json = group_content_from_json;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    @Override
    public String toString() {
        return "MyGroup{" +
                "groupID='" + groupID + '\'' +
                ", groupName='" + groupName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", groupCreationDate='" + groupCreationDate + '\'' +
                ", group_content_from_json='" + group_content_from_json + '\'' +
                ", participantsCount=" + participantsCount +
                '}';
    }
}
