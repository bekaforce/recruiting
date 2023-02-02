package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Hooligan;

import java.time.LocalDate;
import java.util.List;

public interface HooliganService {
    List<Hooligan> all();
    boolean isCandidateHooligan(String name, String surname, LocalDate dateOfBirth);
}
