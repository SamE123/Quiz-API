package com.cooksys.quiz_api.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.quiz_api.dtos.*;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;
import org.springframework.stereotype.Service;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.entities.*;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	
  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final QuestionMapper questionMapper;
  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;
  
  @Override
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }
  
  @Override
  public QuizResponseDto createQuiz(Quiz q){
	  
	  for(Question question : q.getQuestions()) {
		  question.setQuiz(q);
		  
		  for(Answer a : question.getAnswers()) {
			  a.setQuestion(question);
		  }
	  }
	  
	  Quiz saved = quizRepository.save(q);
	  return quizMapper.entityToDto(saved);
  }
  
  @Override
  public QuizResponseDto deleteQuiz(long id) {
	  Quiz erased = quizRepository.getById(id);
	  quizRepository.deleteById(id);
	  return quizMapper.entityToDto(erased);  
  }
  
  @Override
  public QuizResponseDto renameQuiz(long id, String rename) {
	  quizRepository.getById(id).setName(rename);
	  quizRepository.save(quizRepository.getById(id));
	  
	  return quizMapper.entityToDto(quizRepository.getById(id));
  }
  
  @Override
  public QuestionResponseDto getRandom(long id){
	  ArrayList<Question> qList = new ArrayList(quizRepository.getById(id).getQuestions());
	  int rand = (int) (Math.random()*qList.size());
	  return questionMapper.entityToDto(qList.get(rand));
	  
  }
  
  @Override
  public QuizResponseDto addQuestion(long id, Question q){
	  
	
	  
	  Quiz changedQuiz = quizRepository.getById(id);
	  
	  //update question
	  q.setQuiz(changedQuiz);
	  questionRepository.save(q);
	  
	  //update answers
	  
	  for(Answer a : q.getAnswers()) {
		 a.setQuestion(q); 
		 answerRepository.save(a);
	  }
	  
	  //update quiz
	  List<Question> changedList = changedQuiz.getQuestions();
	  changedList.add(q);
	  changedQuiz.setQuestions(changedList);
	  quizRepository.save(changedQuiz);
	 
	  return quizMapper.entityToDto(changedQuiz);
	 
  }
  
  
  @Override
  public QuestionResponseDto deleteQuestion(long id, long qid) {
	  
	  
	  //also deletes question/answers from question/answer table through cascades
	  
	  Question deleteMe = questionRepository.getById(qid);
	  Quiz changeMe = quizRepository.getById(id);
	  
	  changeMe.getQuestions().remove(deleteMe);
	  quizRepository.save(changeMe);
	  	  
	  return questionMapper.entityToDto(deleteMe);
	  
  }
  

}
