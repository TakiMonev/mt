package com.church.mt.controller;

import com.church.mt.model.Question;
import com.church.mt.model.Score;
import com.church.mt.service.ScoreService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/score")
public class ScoreController {
    private ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/score")
    public String getQuestion(Model model) {
        try {
            Long teamScore1 = scoreService.getTeamScore("team1");
            Long teamScore2 = scoreService.getTeamScore("team2");
            Long teamScore3 = scoreService.getTeamScore("team3");
            Long teamScore4 = scoreService.getTeamScore("team4");

            model.addAttribute("team1Score", teamScore1);
            model.addAttribute("team2Score", teamScore2);
            model.addAttribute("team3Score", teamScore3);
            model.addAttribute("team4Score", teamScore4);

            return "score";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
