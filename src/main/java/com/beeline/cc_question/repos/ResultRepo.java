package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result, Integer> {
}
