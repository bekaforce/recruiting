package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    Message findMessageById(Long id);
}
