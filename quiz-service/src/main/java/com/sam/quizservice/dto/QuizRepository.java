package com.sam.quizservice.dto;


import com.sam.quizservice.Model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<QuizModel,Integer> {

}
