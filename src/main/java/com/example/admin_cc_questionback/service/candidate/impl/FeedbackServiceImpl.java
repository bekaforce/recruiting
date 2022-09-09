package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Feedback;
import com.example.admin_cc_questionback.repository.candidate.FeedbackRepo;
import com.example.admin_cc_questionback.service.candidate.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepo feedbackRepo;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }


    @Override
    public List<Feedback> all() {
        return feedbackRepo.findAll();
    }

    @Override
    public boolean delete(Long id) {
        Feedback feedback = feedbackById(id);
        if (feedback != null){
            feedbackRepo.delete(feedback);
            return true;
        }
        return false;
    }

    @Override
    public Feedback feedbackById(Long id) {
        return feedbackRepo.findFeedbackById(id);
    }
}
