package com.hnp.alfrescorestapi.dto;

import java.time.LocalDateTime;

public class GenerealResponse {

    private LocalDateTime timeStamp;
    private int status;
    private String description;

    public GenerealResponse() {}

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
