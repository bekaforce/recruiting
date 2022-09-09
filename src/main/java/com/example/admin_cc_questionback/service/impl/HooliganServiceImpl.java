package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Hooligan;
import com.example.admin_cc_questionback.entities.dtos.HooliganDto;
import com.example.admin_cc_questionback.repository.HooliganRepo;
import com.example.admin_cc_questionback.service.HooliganService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HooliganServiceImpl implements HooliganService {
    private final HooliganRepo hooliganRepo;

    public HooliganServiceImpl(HooliganRepo hooliganRepo) {
        this.hooliganRepo = hooliganRepo;
    }

    @Override
    public Hooligan save(HooliganDto hooliganDto) {
        Hooligan hooligan = new Hooligan();
        hooligan.setName(hooliganDto.getName());
        hooligan.setBirthday(hooliganDto.getBirthday());
        hooligan.setReason(hooliganDto.getReason());
        hooliganRepo.save(hooligan);
        return hooligan;
    }

    @Override
    public Hooligan update(Long id, HooliganDto hooliganDto) {
        Hooligan hooligan = hooliganRepo.findHooliganById(id);
        if (hooligan != null){
            hooligan.setName(hooligan.getName());
            hooligan.setBirthday(hooliganDto.getBirthday());
            hooligan.setReason(hooliganDto.getReason());
            hooliganRepo.save(hooligan);
            return hooligan;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Hooligan hooligan = hooliganRepo.findHooliganById(id);
        if (hooligan != null){
            hooliganRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Hooligan> all() {
        return hooliganRepo.findAll();
    }
}
