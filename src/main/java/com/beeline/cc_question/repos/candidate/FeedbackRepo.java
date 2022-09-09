package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

}
