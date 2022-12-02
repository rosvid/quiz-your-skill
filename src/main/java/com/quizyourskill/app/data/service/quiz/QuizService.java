package com.quizyourskill.app.data.service.quiz;

import com.quizyourskill.app.data.entity.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class QuizService{
   private final QuizRepository quizRepository;

   @Autowired
   public QuizService(QuizRepository quizRepository){
      this.quizRepository = quizRepository;
   }

   public Optional<Quiz> findById(Integer id){
      return quizRepository.findById(id);
   }

   public Optional<Set<Quiz>> findAllByCourseId(Integer courseId){
      return quizRepository.findAllByCourseId(courseId);
   }
}
