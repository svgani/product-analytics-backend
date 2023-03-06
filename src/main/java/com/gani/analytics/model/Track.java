package com.gani.analytics.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="track")
public class Track {

    private String link;
    private Date date;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Track{" +
                "link='" + link + '\'' +
                ", date=" + date +
                ", userId='" + userId + '\'' +
                '}';
    }
}
