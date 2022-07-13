package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Hooligan;

import java.time.LocalDate;
import java.util.List;

public interface HooliganService {
    List<Hooligan> all();
    boolean checkCandidate(String name, LocalDate date);
}