package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Hooligan;
import com.beeline.cc_question.repos.HooliganRepo;
import com.beeline.cc_question.services.HooliganService;
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
    public boolean checkCandidate(String name, LocalDate date) {
        List<Hooligan> hooligans = all();
        String[] array = name.split(" ");
        for (Hooligan hooligan : hooligans){
            for (String part : array){
                if (hooligan.getName().toLowerCase().contains(part.toLowerCase()) && hooligan.getBirthday().isEqual(date)){
                    return true;
                }
            }
        }
        return false;
    }
}
