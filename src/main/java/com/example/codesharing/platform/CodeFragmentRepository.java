package com.example.codesharing.platform;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeFragmentRepository extends CrudRepository<CodeFragment, Long>, JpaSpecificationExecutor<CodeFragment> {
    List<CodeFragment> findTop10ByOrderByDateDesc();

    CodeFragment findTopByOrderByIdDesc();
}
