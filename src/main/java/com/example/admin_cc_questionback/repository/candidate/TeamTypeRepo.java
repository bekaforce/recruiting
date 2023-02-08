package com.example.admin_cc_questionback.repository.candidate;

import com.example.admin_cc_questionback.entities.candidate.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamTypeRepo extends JpaRepository<TeamType, Long> {
    TeamType findTeamTypeById(Long id);

    @Query(value = "select * from vcv.team_type t order by t.id", nativeQuery = true)
    List<TeamType> findAllOrderById();
}
