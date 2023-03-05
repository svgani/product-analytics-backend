package com.gani.analytics.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="id")
public class Identify {
    private String userId;
    private Date date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Identify{" +
                "userId='" + userId + '\'' +
                ", date=" + date +
                '}';
    }
}
