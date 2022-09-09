package com.example.admin_cc_questionback.entities;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "participant", schema = "vcv")
public class Participant {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "participant_seq")
    @SequenceGenerator(name = "participant_seq", initialValue = 1, allocationSize = 1, sequenceName = "participant_id_seq")
    private Long id;
    private String name;
    private String lastName;
    private Boolean gender;
    private LocalDate birthday;
    private String email;
    private String address;
    private String candidate_type;
    private String phone_number;
    private String status;
    private String city;
    private LocalDate invitation_date;
}
