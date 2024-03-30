package com.sam.quizservice.service;


import com.sam.quizservice.Model.QuestionWrapper;
import com.sam.quizservice.Model.QuizModel;
import com.sam.quizservice.Model.Response;
import com.sam.quizservice.dto.QuizRepository;
import com.sam.quizservice.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        QuizModel quiz = new QuizModel();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        QuizModel quiz =  quizRepository.findById(id).get();
        List<Integer> questionIDs = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questionForUser = quizInterface.getQuestionsFromId(questionIDs);
        return questionForUser;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
