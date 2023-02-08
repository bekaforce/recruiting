package com.example.admin_cc_questionback.service.interview;

import com.example.admin_cc_questionback.entities.interview.Essay;

public interface EssayService {
    String comment(String comment, Long id);
    Essay essayById(Long id);
}
