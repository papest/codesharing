package com.example.codesharing.platform;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


@Controller
public class SecondController {


    private final CodeFragmentService codeFragmentService;

    public SecondController(@Autowired CodeFragmentService codeFragmentService) {
        this.codeFragmentService = codeFragmentService;
    }


    @GetMapping(value = "/code/{id}")
    public ModelAndView getHtmlFragment(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        if (codeFragmentService.findCodeFragmentById(id).isEmpty()) {
            return modelAndView;
        }

        CodeFragment codeFragment = codeFragmentService.findCodeFragmentById(id).get();
        Date dateNow = new Date();
        if ((codeFragment.isLimitedViews() && codeFragment.getViews() < 1)
                || (codeFragment.isLimitedTime() && codeFragment.getEndDate().before(dateNow))) {
            codeFragmentService.delete(codeFragment.getId());
            return modelAndView;
        }
        int views = codeFragment.getViews();

        if (codeFragment.isLimitedViews()) {
            codeFragment.setViews(--views);
            codeFragmentService.save(codeFragment);
        }


        long time = codeFragment.isLimitedTime() ? (codeFragment.getEndDate().getTime()
                - dateNow.getTime()) / 1000 : 0;
        if (codeFragment.isLimitedTime() && time <= 0) {
            codeFragmentService.delete(codeFragment.getId());
            return modelAndView;
        }
        modelAndView.setViewName("fragment");
        modelAndView.setStatus(HttpStatus.OK);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")
                .withZone(ZoneId.systemDefault()).withLocale(Locale.ENGLISH);

        modelAndView.addObject("date", dateTimeFormatter.format(codeFragment.getDate().toInstant()));
        modelAndView.addObject("code", codeFragment.getCode());
        if (codeFragment.isLimitedTime()) {
            modelAndView.addObject("time", time);
        }
        if (codeFragment.isLimitedViews()) {
            modelAndView.addObject("views", views);
        }


        return modelAndView;


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
