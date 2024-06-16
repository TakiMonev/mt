package com.church.mt.repository;

import com.church.mt.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public Optional<Question> findByQuestionName(String question);

    @Query("SELECT q FROM Question q WHERE q.questionName = ?1 AND q.answer = ?2")
    public Optional<Question> findAnswer(String questionName, String answer);
}
