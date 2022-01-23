package com.example.codesharing.platform;


import javax.persistence.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Map;
import java.util.UUID;

import static java.util.Locale.ENGLISH;


@Entity
@Table(name = "CodeFragments")
public class CodeFragment {

    @Id
    private UUID id;


    @Column(name = "date")
    private Date date;

    @Column(name = "code")
    private String code = "";


    @Column(name = "limitedTime")
    private boolean limitedTime = false;


    @Column(name = "endDate")
    private Date endDate = null;


    @Column(name = "limitedViews")
    private boolean limitedViews = false;

    @Column(name = "views")
    private int views = 0;


    public CodeFragment(String code) {
        this();
        this.code = code;

    }

    public boolean isLimitedTime() {
        return limitedTime;
    }

    public void setLimitedTime(boolean limitedTime) {
        this.limitedTime = limitedTime;
    }

    public boolean isLimitedViews() {
        return limitedViews;
    }

    public void setLimitedViews(boolean limitedViews) {
        this.limitedViews = limitedViews;
    }

    public CodeFragment(UUID id, Date date, String code, boolean limitedTime, Date endDate, boolean limitedViews, int views) {
        this.id = id;
        this.date = date;
        this.code = code;
        this.limitedTime = limitedTime;
        this.limitedViews = limitedViews;
        if (limitedTime) {
            this.endDate = endDate;
        }
        if (limitedViews) {
            this.views = views;
        }
    }

    public CodeFragment(UUID id, String code) {
        this(code);
        this.id = id;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public CodeFragment() {
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }


    public Map<String, String> returnMap() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")
                .withZone(ZoneId.systemDefault()).withLocale(ENGLISH);
        long time = limitedTime ? (endDate.getTime() - new Date().getTime()) / 1000 : 0;
        Map<String, String> map = Map.of("code", getCode(), "date", dateTimeFormatter.format(getDate().toInstant()),
                "time", Long.toString(time), "views", Integer.toString(views));
        return map;
    }


}
