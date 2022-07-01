package com.example.admin_cc_questionback.repository.interview;

import com.example.admin_cc_questionback.entities.interview.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Answer getAnswerById(Long id);
}
