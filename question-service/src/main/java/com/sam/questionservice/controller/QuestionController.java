package com.sam.questionservice.controller;


import com.sam.questionservice.Model.QuestionModel;
import com.sam.questionservice.Model.QuestionWrapper;
import com.sam.questionservice.Model.Response;
import com.sam.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping("question")
@EnableWebMvc
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;
    @GetMapping("allQuestion")
    public ResponseEntity<List<QuestionModel>> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<QuestionModel>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionModel question)
    {
        return questionService.addQuestion(question);
    }

    //generate
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestion)
    {
        return questionService.getQuestionForQuiz(categoryName,numQuestion);
    }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    //getScore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }

}
