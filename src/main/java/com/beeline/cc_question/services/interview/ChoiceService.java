package com.beeline.cc_question.services.interview;

import java.util.List;

public interface ChoiceService {
    String chooseEssayOrVideo(Long participantId, String typeOfInterview);
    List<String> getEssayAndVideo();
}
