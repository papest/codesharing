package com.example.codesharing.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CodeFragmentService {

    private final CodeFragmentRepository codeFragmentRepository;

    @Autowired
    public CodeFragmentService(CodeFragmentRepository codeFragmentRepository) {
        this.codeFragmentRepository = codeFragmentRepository;
    }

    public Optional<CodeFragment> findCodeFragmentById(UUID id) {
        return codeFragmentRepository.findById(id);
    }

    public CodeFragment save(CodeFragment toSave) {

        return codeFragmentRepository.save(toSave);
    }

    public List<CodeFragment> findLatest() {
        return codeFragmentRepository.findTop10ByLimitedTimeIsFalseAndLimitedViewsIsFalseOrderByDateDesc();
    }



    public void delete(UUID id) {
        codeFragmentRepository.deleteById(id);
    }
}

