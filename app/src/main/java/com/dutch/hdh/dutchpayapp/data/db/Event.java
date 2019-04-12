package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("board_No")
    private String eventID;

    @SerializedName("board_Type")
    private String eventType;

    @SerializedName("board_Title")
    private String eventTitle;

    @SerializedName("board_Content")
    private String eventContent;

    @SerializedName("writer_Date")
    private String eventWriteDate;

    @SerializedName("status")
    private String eventStatus;

    @SerializedName("upload_Name")
    private String eventUploadName;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventWriteDate() {
        return eventWriteDate;
    }

    public void setEventWriteDate(String eventWriteDate) {
        this.eventWriteDate = eventWriteDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventUploadName() {
        return eventUploadName;
    }

    public void setEventUploadName(String eventUploadName) {
        this.eventUploadName = eventUploadName;
    }
}