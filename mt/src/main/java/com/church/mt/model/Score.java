package com.church.mt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long scoreId;
    String question;
    String team;
    Long score;

    public Score(String question, String team, Long score) {
        this.question = question;
        this.team = team;
        this.score = score;
    }
}
