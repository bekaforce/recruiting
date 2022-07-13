package com.beeline.cc_question.entities.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "roles", schema = "vcv")
@Data
public class Role {
    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
