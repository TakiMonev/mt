package com.church.mt.controller;

import com.church.mt.model.Question;
import com.church.mt.service.QuestionService;
import com.church.mt.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("")
@Slf4j
public class QuestionController {
    private QuestionService questionService;
    private ScoreService scoreService;

    @Autowired
    public QuestionController(QuestionService questionService, ScoreService scoreService) {
        this.questionService = questionService;
        this.scoreService = scoreService;
    }

    @GetMapping("/{question}")
    public String getQuestion(@PathVariable("question") String question,
                              Model model) {
        try {
            Optional<Question> myQuestion = questionService.findQuestion(question);
            model.addAttribute("info", myQuestion.get());
            model.addAttribute("imageUrl",
                    "https://churchmt.s3.ap-northeast-2.amazonaws.com/static/" + question + ".jpg");

            return "main";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @PostMapping("/{question}/post")
    public String submitAnswer(@PathVariable("question") String question,
                               @RequestParam("team") String team,
                               @RequestParam("answer") String answer) {
        try {
            Optional<Question> questionAnswer = questionService.findAnswer(question, answer);
            if (questionAnswer.isPresent()) {
                System.out.println(scoreService.isSolvable(question, team));
                if (scoreService.isSolvable(question, team).equals("success")) {
                    scoreService.saveScore(question, team);
                    return "success";
                } else {
                    return "again";
                }
            } else {
                return "fail";
            }
        } catch (Exception error) {
            return "fail";
        }
    }
}
