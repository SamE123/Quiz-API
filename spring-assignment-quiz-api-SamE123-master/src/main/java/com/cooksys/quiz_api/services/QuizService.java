package com.cooksys.quiz_api.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.entities.Question;

public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();
  QuizResponseDto createQuiz(Quiz q);
  QuizResponseDto deleteQuiz(long id);
  QuizResponseDto renameQuiz(long id, String newName);
  QuestionResponseDto getRandom(long id);
  QuizResponseDto addQuestion(long id, Question q);
  QuestionResponseDto deleteQuestion(long id, long qid);
}

