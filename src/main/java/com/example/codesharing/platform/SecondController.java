package com.example.codesharing.platform;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@Controller
public class SecondController {


    private final CodeFragmentService codeFragmentService;

    public SecondController(@Autowired CodeFragmentService codeFragmentService) {
        this.codeFragmentService = codeFragmentService;
    }

    @GetMapping(value = "/code/{id}")
    public String getHtmlFragment(@PathVariable long id, Model model) {

        CodeFragment codeFragment = codeFragmentService.findCodeFragmentById(id).orElse(null);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")
                .withZone(ZoneId.systemDefault()).withLocale(Locale.ENGLISH);

        model.addAttribute("date",
                codeFragment == null ? "" : dateTimeFormatter.format(codeFragment.getDate().toInstant()));
        model.addAttribute("code", codeFragment == null ? "" : codeFragment.getCode());

        return "fragment";
    }


    @GetMapping(value = "/code/latest")
    public String getHtmlFragments(Model model) {
        List<CodeFragment> list = codeFragmentService.findLatest();
        model.addAttribute("fragments", list);
        return "latest";
    }

    @GetMapping(value = "/code/new")
    public String newCode() {

        return "new";
    }
}
