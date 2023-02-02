package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Hooligan;
import com.beeline.cc_question.repos.candidate.HooliganRepo;
import com.beeline.cc_question.services.candidate.HooliganService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HooliganServiceImpl implements HooliganService {
    private final HooliganRepo hooliganRepo;

    public HooliganServiceImpl(HooliganRepo hooliganRepo) {
        this.hooliganRepo = hooliganRepo;
    }

    @Override
    public List<Hooligan> all() {
        return hooliganRepo.findAll();
    }

    @Override
    public boolean isCandidateHooligan(String name, String surname, LocalDate dateOfBirth) {
        List<Hooligan> hooligans = all();
        for (Hooligan hooligan : hooligans){
                if (hooligan.getName().equalsIgnoreCase(name) && hooligan.getSurname().equalsIgnoreCase(surname) && hooligan.getBirthday().isEqual(dateOfBirth)){
                    return true;
                }
        }
        return false;
    }
}
