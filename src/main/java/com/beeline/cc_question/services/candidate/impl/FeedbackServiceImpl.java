package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Feedback;
import com.beeline.cc_question.entities.dtos.candidate.FeedbackDto;
import com.beeline.cc_question.repos.candidate.FeedbackRepo;
import com.beeline.cc_question.services.candidate.FeedbackService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepo feedbackRepo;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    @Override
    public Feedback save(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setName(feedbackDto.getName());
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setComment(feedbackDto.getComment());
        feedback.setDate(LocalDateTime.now(ZoneId.of("Asia/Bishkek")));
        return feedbackRepo.save(feedback);
    }
}
