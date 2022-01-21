package com.example.codesharing.platform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FirstController {
    String simpleCodeFragment =
"public static void main(String[] args) {\n" +
"SpringApplication.run(CodeSharingPlatform.class, args);\n" +
"}";


    @GetMapping(value = "/api/code")
    public Map<String,String> getApiCode() {

        return Map.of("code", simpleCodeFragment);
    }

    @GetMapping(value = "/code")
    public String getHtmlFragment() {

        return "<html>\n" +
               "<head>\n" +
               "<title>Code</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "<pre>\n" +
               simpleCodeFragment +
               "</pre>\n" +
               "</body>\n" +
               "</html>\n";

    }
}