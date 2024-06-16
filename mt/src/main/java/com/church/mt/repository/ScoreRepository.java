package com.church.mt.repository;

import com.church.mt.model.Question;
import com.church.mt.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    public Optional<Score> findByQuestionAndTeam(String question, String team);
    @Query("SELECT COUNT(s) FROM Score s WHERE s.question = ?1")
    public Long getSolvedOrder(String question);
}
