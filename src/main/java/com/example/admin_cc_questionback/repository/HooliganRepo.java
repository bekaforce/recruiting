package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Hooligan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooliganRepo extends JpaRepository<Hooligan, Long> {
    Hooligan findHooliganById(Long id);
}
