package com.example.codesharing.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class FirstController {

    final CodeFragmentService codeFragmentService;


    public FirstController(@Autowired CodeFragmentService codeFragmentService) {
        this.codeFragmentService = codeFragmentService;
    }

    @GetMapping(value = "/api/code/{id}")
    public Map<String, String> getApiCode(@PathVariable long id) {

        if (codeFragmentService.findCodeFragmentById(id).isEmpty()) {
            return Map.of();
        }


        CodeFragment codeFragment = codeFragmentService.findCodeFragmentById(id).get();

        return codeFragment.returnMap();

    }


    @GetMapping(value = "/api/code/latest")
    public List<Map<String, String>> getApiLatest() {
        List<CodeFragment> list = codeFragmentService.findLatest();
        if (list.isEmpty()) {
            return List.of();
        }

        return list.stream()
                .map(CodeFragment::returnMap)
                .collect(Collectors.toList());
    }


    @PostMapping(value = "/api/code/new")
    public Map<String, String> newApiCode(@RequestBody Map<String, String> requestMap) {
        long nextId = codeFragmentService.lastId() + 1;
        codeFragmentService.save(new CodeFragment(nextId, requestMap.get("code")));
        return Map.of("id", Long.toString(nextId));
    }


}


