package com.church.mt.service;

import com.church.mt.model.Question;
import com.church.mt.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> findQuestion(String questionName) {
        Optional<Question> myQuestion = questionRepository.findByQuestionName(questionName);
        System.out.println("myQuestion : " + myQuestion.get().getQuestionName());
        return myQuestion;
    }

    public Optional<Question> findAnswer(String question, String answer) {
        try {
            Optional<Question> questionAnswer = questionRepository.findAnswer(question, answer);
            return questionAnswer;
        } catch (DataException error) {
            throw error;
        }
    }
}
