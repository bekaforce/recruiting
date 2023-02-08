package com.example.admin_cc_questionback.repository.interview;

import com.example.admin_cc_questionback.entities.interview.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepo extends JpaRepository<Essay, Long> {
    Essay findEssayById(Long essay_id);
}
