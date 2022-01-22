package com.example.codesharing.platform;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


@Entity
@Table(name = "CodeFragments")
public class CodeFragment {

    @Id
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "code")
    private String code = "";

    public CodeFragment(String code) {
        this();
        this.code = code;

    }

    public CodeFragment(Long id, Date date, String code) {
        this.date = date;
        this.code = code;
        this.id = id;

    }

    public CodeFragment(long id, String code) {
        this(code);
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
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
                .withZone(ZoneId.systemDefault()).withLocale(Locale.ENGLISH);

        return Map.of("code", getCode(), "date", dateTimeFormatter.format(getDate().toInstant()));
    }


}
