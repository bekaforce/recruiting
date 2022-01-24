package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
}
