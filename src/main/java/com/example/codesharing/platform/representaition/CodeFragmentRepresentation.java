package com.example.codesharing.platform.representaition;


import com.example.codesharing.platform.CodeFragment;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import static java.util.Locale.ENGLISH;

public class CodeFragmentRepresentation {
   private String id;
   private String code;
   private String date;
   private long time;
   private int views;


    public CodeFragmentRepresentation(CodeFragment codeFragment) {

        code = codeFragment.getCode();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")
                .withZone(ZoneId.systemDefault()).withLocale(ENGLISH);
        date = dateTimeFormatter.format(codeFragment.getDate().toInstant());
        time = codeFragment.isLimitedTime() ? (codeFragment.getEndDate().getTime() - new Date().getTime()) / 1000 : 0;
        views = codeFragment.getViews();
    }



    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public Long getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }
}
