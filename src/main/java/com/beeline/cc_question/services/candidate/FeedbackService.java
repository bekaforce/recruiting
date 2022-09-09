package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Feedback;
import com.beeline.cc_question.entities.dtos.candidate.FeedbackDto;

public interface FeedbackService {
    Feedback save(FeedbackDto feedbackDto);
}
