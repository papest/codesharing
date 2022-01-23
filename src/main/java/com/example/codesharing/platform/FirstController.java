package com.example.codesharing.platform;


import com.example.codesharing.platform.representaition.CodeFragmentRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<CodeFragmentRepresentation> getApiCode(@PathVariable UUID id) {

        ResponseEntity<CodeFragmentRepresentation> notFoundResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);


        if (codeFragmentService.findCodeFragmentById(id).isEmpty()) {
            return notFoundResponseEntity;
        }

        CodeFragment codeFragment = codeFragmentService.findCodeFragmentById(id).get();

        if (codeFragment.isLimitedTime() && codeFragment.getEndDate().before(new Date())) {
            codeFragmentService.delete(codeFragment.getId());
            return notFoundResponseEntity;
        }

        if (codeFragment.isLimitedViews()) {

            if (codeFragment.getViews() < 1) {
                codeFragmentService.delete(codeFragment.getId());
                return notFoundResponseEntity;
            }

            int views = codeFragment.getViews();
            codeFragment.setViews(--views);
            codeFragmentService.save(codeFragment);
        }


        return new ResponseEntity<>(new CodeFragmentRepresentation(codeFragment), HttpStatus.OK);

    }


    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<List<CodeFragmentRepresentation>> getApiLatest() {
        List<CodeFragment> list = codeFragmentService.findLatest();

        return new ResponseEntity<>(list.stream()
                .map(CodeFragmentRepresentation::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    @PostMapping(value = "/api/code/new")
    public Map<String, String> newApiCode(@RequestBody Map<String, String> requestMap) {
        UUID nextId = UUID.randomUUID();
        Date date = new Date();
        long time = Long.parseLong(requestMap.get("time"));
        int views = Integer.parseInt(requestMap.get("views"));
        codeFragmentService.save(new CodeFragment(nextId, date, requestMap.get("code"),
                time > 0, new Date(date.getTime() + time * 1000), views > 0,
                views));
        return Map.of("id", nextId.toString());
    }


}


