package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> all();
    boolean delete(Long id);
    Feedback feedbackById(Long id);
}
