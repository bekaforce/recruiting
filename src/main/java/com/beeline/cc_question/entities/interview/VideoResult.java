package com.beeline.cc_question.entities.interview;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "videoResult", schema = "vcv")
public class VideoResult {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "video_seq")
    @SequenceGenerator(name = "video_seq", initialValue = 1, allocationSize = 1, sequenceName = "video_id_seq")
    private Long id;
    private String videoName;
    private LocalDateTime uploadedTime;
    private String question;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    private String comment;
    private Long position;

    public VideoResult(String videoName, LocalDateTime uploadedTime, Candidate candidate, String question, String comment, Long position) {
        this.videoName = videoName;
        this.uploadedTime = uploadedTime;
        this.question = question;
        this.candidate = candidate;
        this.comment = comment;
        this.position = position;
    }

    public VideoResult() {

    }
}
