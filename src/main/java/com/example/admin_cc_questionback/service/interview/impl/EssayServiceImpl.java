package com.example.admin_cc_questionback.service.interview.impl;

import com.example.admin_cc_questionback.entities.interview.Essay;
import com.example.admin_cc_questionback.entities.interview.VideoResult;
import com.example.admin_cc_questionback.repository.interview.EssayRepo;
import com.example.admin_cc_questionback.service.interview.EssayService;
import org.springframework.stereotype.Service;

@Service
public class EssayServiceImpl implements EssayService {
    private final EssayRepo essayRepo;

    public EssayServiceImpl(EssayRepo essayRepo) {
        this.essayRepo = essayRepo;
    }

    @Override
    public String comment(String comment, Long id) {
        Essay essay = essayById(id);
        if (essay != null){
            essay.setComment(comment);
            essayRepo.save(essay);
            return comment;
        }
        return null;
    }

    @Override
    public Essay essayById(Long id) {
        return essayRepo.findEssayById(id);
    }
}
