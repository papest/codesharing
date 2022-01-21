package com.example.codesharing.platform;

import java.util.Date;
import java.util.Map;

public class CodeFragment {
    Date date;
    String code = "";

    public CodeFragment(String code) {
        this();
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
        return Map.of("code", getCode(), "date", getDate().toString());
    }


}
