package com.example.codesharing.platform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FirstController {
    CodeFragment simpleCodeFragment = new CodeFragment(
"public static void main(String[] args) {\n" +
"SpringApplication.run(CodeSharingPlatform.class, args);\n" +
"}");


    @GetMapping(value = "/api/code")
    public Map<String,String> getApiCode() {

        return Map.of("code", simpleCodeFragment.getCode(), "date", simpleCodeFragment.getDate().toString());
    }

    @GetMapping(value = "/code")
    public String getHtmlFragment() {

        return "<html>\n" +
               "<head>\n" +
               "<title>Code</title>\n" +
                "<style>pre{ border:groove; background-color:lightgrey; } span{ color:green;} </style>" +
               "</head>\n" +
               "<body>\n" +
               "<span id=\"load_date\">" + simpleCodeFragment.getDate().toString() + "</span>" +
               "<pre id=\"code_snippet\">\n" +
               simpleCodeFragment.getCode() +
               "</pre>\n" +
               "</body>\n" +
               "</html>\n";

    }
    @PostMapping(value = "/api/code/new")
    public Map newApiCode(@RequestBody Map<String,String> requestMap) {
        simpleCodeFragment = new CodeFragment(requestMap.get("code"));
        return Map.of();
    }

    @GetMapping(value = "/code/new")
    public String newCode() {
        return
                "<html>\n" +
                "<head>\n" +
                "<title>Create</title>\n" +
                "</head>\n" +
                "<body>\n" +
                        "<form>" +
                        "<script>" +
                        "function send() {\n" +
                        "    let object = {\n" +
                        "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                        "    };\n" +
                        "    \n" +
                        "    let json = JSON.stringify(object);\n" +
                        "    \n" +
                        "    let xhr = new XMLHttpRequest();\n" +
                        "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                        "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                        "    xhr.send(json);\n" +
                        "    \n" +
                        "    if (xhr.status == 200) {\n" +
                        "      alert(\"Success!\");\n" +
                        "    }\n" +
                        "}" +
                        "</script>"+
                "<p><textarea id=\"code_snippet\">//write your code here</textarea></p>" +
                        "<p><button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button></p>" +
                        "</form>" +
                "</body>\n" +
                "</html>\n";

    }
}


