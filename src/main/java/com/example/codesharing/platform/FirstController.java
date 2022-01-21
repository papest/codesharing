package com.example.codesharing.platform;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@RestController
public class FirstController {
    static final int LATEST_CONSTANT = 10;

    static CopyOnWriteArrayList<CodeFragment> list = new CopyOnWriteArrayList<>();

    @GetMapping(value = "/api/code/{id}")
    public Map<String, String> getApiCode(@PathVariable int id) {
        if (id >= list.size()) {
            return Map.of();
        }
        return list.get(id).returnMap();
    }

    @GetMapping(value = "/api/code/latest")
    public List<Map<String, String>> getApiLatest() {
        if (list.isEmpty()) {
            return List.of();
        }

        return list.stream().sorted((a, b) -> (int) (b.getDate().getTime() - a.getDate().getTime()))
                .limit(LATEST_CONSTANT)
                .map(CodeFragment::returnMap)
                .collect(Collectors.toList());
    }


    @PostMapping(value = "/api/code/new")
    public Map<String, String> newApiCode(@RequestBody Map<String, String> requestMap) {
        list.add(new CodeFragment(requestMap.get("code")));
        return Map.of("id", String.valueOf(list.size() - 1));
    }


}


