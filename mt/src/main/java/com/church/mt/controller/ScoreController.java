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
}
