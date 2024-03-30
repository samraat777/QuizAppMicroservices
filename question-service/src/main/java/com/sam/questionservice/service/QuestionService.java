package com.sam.questionservice.service;


import com.sam.questionservice.Model.QuestionModel;
import com.sam.questionservice.Model.QuestionWrapper;
import com.sam.questionservice.Model.Response;
import com.sam.questionservice.dto.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<QuestionModel>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<QuestionModel>> getQuestionsByCategory( @PathVariable("category") String category){
       // System.out.println("============CATEGORY===============       " + category);
        try{
            return new ResponseEntity<>(questionRepository.getQuestionByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(QuestionModel question) {
        try{
            questionRepository.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer> questions = questionRepository.findRandomQuestionByCategory(categoryName,numQuestion);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Integer id : questionIds){
            QuestionModel q = questionRepository.findById(id).get();
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestion());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;

        for(Response response :responses)
        {
            QuestionModel questionModel = questionRepository.findById(response.getId()).get();
            if(response.getQuestionAnswer().equals(questionModel.getAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
