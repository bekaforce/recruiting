package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Answer getAnswerById(Long id);
}
