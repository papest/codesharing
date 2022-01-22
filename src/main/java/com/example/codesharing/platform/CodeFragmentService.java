package com.example.codesharing.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CodeFragmentService {

    private final CodeFragmentRepository codeFragmentRepository;

    @Autowired
    public CodeFragmentService(CodeFragmentRepository codeFragmentRepository) {
        this.codeFragmentRepository = codeFragmentRepository;
    }

    public Optional<CodeFragment> findCodeFragmentById(Long id) {
        return codeFragmentRepository.findById(id);
    }

    public CodeFragment save(CodeFragment toSave) {

        return codeFragmentRepository.save(toSave);
    }

    public List<CodeFragment> findLatest() {
        return codeFragmentRepository.findTop10ByOrderByDateDesc();
    }


    public long lastId() {
        CodeFragment codeFragment = codeFragmentRepository.findTopByOrderByIdDesc();
        if (codeFragment == null) {
            return -1;
        }
        return codeFragmentRepository.findTopByOrderByIdDesc().getId();
    }
}
