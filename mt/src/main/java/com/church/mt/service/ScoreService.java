package com.church.mt.service;

import com.church.mt.model.Question;
import com.church.mt.model.Score;
import com.church.mt.repository.QuestionRepository;
import com.church.mt.repository.ScoreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ScoreService {
    private ScoreRepository scoreRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository,
                        QuestionRepository questionRepository) {
        this.scoreRepository = scoreRepository;
        this.questionRepository = questionRepository;
    }

    public void saveScore(String question, String team) {
        try {
            Optional<Question> questionWhichISolved = questionRepository.findByQuestionName(question);
            if (questionWhichISolved.isEmpty()) {
                throw new NullPointerException("데이터가 존재하지 않습니다.");
            }

            Long maxScore = questionWhichISolved.get().getScore();
            Long dividedScore = maxScore / 5;
            Long solvedOrder = scoreRepository.getSolvedOrder(question);
            Long myTeamScore = maxScore - solvedOrder * dividedScore;

            Score questionScore = new Score(question, team, myTeamScore);
            scoreRepository.saveAndFlush(questionScore);
        } catch (DataException error) {
            throw error;
        }
    }

    @Transactional
    public String isSolvable(String question, String team) {
        if (scoreRepository.findByQuestionAndTeam(question, team).isPresent()) {
            return "again";
        } else {
            return "success";
        }
    }

    public Long getTeamScore(String teamNum) {
        Long teamNScore = scoreRepository.getTeamScore(teamNum);
        return teamNScore;
    }
}
