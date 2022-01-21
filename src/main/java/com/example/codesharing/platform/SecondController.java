package com.example.codesharing.platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.stream.Collectors;

import static com.example.codesharing.platform.FirstController.*;


@Controller
public class SecondController {
    @GetMapping(value = "/code/{id}")
    public String getHtmlFragment(@PathVariable int id, Model model) {
        model.addAttribute("date",
                id < list.size() ? list.get(id).getDate().toString() : "");
        model.addAttribute("code", id < list.size() ?
                list.get(id).getCode() : "");
        return "fragment";
    }

    @GetMapping(value = "/code/latest")
    public String getHtmlFragments(Model model) {
        model.addAttribute("fragments",
                list.stream().sorted((a, b) -> (int) (b.getDate().getTime() - a.getDate().getTime()))
                        .limit(LATEST_CONSTANT).collect(Collectors.toList()));
        return "latest";
    }

    @GetMapping(value = "/code/new")
    public String newCode(Model model) {

        return "new";
    }
}
