package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Hooligan;
import com.example.admin_cc_questionback.entities.dtos.HooliganDto;

import java.util.List;

public interface HooliganService {
    Hooligan save(HooliganDto hooliganDto);
    Hooligan update(Long id, HooliganDto hooliganDto);
    boolean delete(Long id);
    List<Hooligan> all();
    Hooligan setHooligan(Hooligan hooligan, HooliganDto hooliganDto);
}
