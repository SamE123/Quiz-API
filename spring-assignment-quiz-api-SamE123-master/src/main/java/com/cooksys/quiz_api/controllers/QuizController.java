package com.cooksys.quiz_api.controllers;

import java.util.List;

import com.cooksys.quiz_api.dtos.*;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.entities.Question; 
import com.cooksys.quiz_api.services.QuizService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }
  
  @PostMapping
  public QuizResponseDto createQuiz(@RequestBody Quiz q){
	  return quizService.createQuiz(q);
  }
  
  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuiz(@PathVariable long id) {
	  return quizService.deleteQuiz(id);
  }
  
  @PatchMapping("/{id}/rename/{newName}")
  public QuizResponseDto renameQuiz(@PathVariable long id, @PathVariable String newName) {
	  return quizService.renameQuiz(id, newName);
  }
  
  @GetMapping("/{id}/random")
  public QuestionResponseDto getRandom(@PathVariable long id) {
	  return quizService.getRandom(id);
  }
  
  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestion(@PathVariable long id, @RequestBody Question q) {
	  return quizService.addQuestion(id, q);
  }
  
  @DeleteMapping("/{id}/delete/{questionID}")	
  public QuestionResponseDto deleteQuestion(@PathVariable long id, @PathVariable long questionID) {
	  return quizService.deleteQuestion(id, questionID);
  }
  
  // TODO: Implement the remaining 6 endpoints from the documentation.

}
