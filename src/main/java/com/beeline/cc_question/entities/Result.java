package com.beeline.cc_question.entities;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@TypeDefs({
    @TypeDef(
        name = "int-array",
        typeClass = IntArrayType.class
    )
})
@Data
@Entity
@Table
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_info_id")
    private ResultInfo resultInfo;

    private int questionId;

    @Type(type = "int-array")
    @Column(
        name = "answers_id",
        columnDefinition = "integer[]"
    )
    private int[] answersId;

    public Result() {
    }

    public Result(int questionId, int[] answersId) {
        this.questionId = questionId;
        this.answersId = answersId;
    }
}
