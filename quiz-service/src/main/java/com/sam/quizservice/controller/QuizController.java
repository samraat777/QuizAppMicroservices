package com.sam.quizservice.controller;


import com.sam.quizservice.Model.QuestionWrapper;
import com.sam.quizservice.Model.QuizDTO;
import com.sam.quizservice.Model.Response;
import com.sam.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;



    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumQ(),quizDTO.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        return quizService.calculateResult(id,responses);
    }
}
