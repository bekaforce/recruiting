package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.Hooligan;

import java.time.LocalDate;
import java.util.List;

public interface HooliganService {
    List<Hooligan> all();
    boolean checkCandidate(String name, LocalDate date);
}
