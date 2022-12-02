package com.quizyourskill.app.data.entity.result;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.course.Course;
import com.quizyourskill.app.data.entity.quiz.AnswerOption;
import com.quizyourskill.app.data.entity.quiz.Quiz;
import com.quizyourskill.app.data.entity.quiz.QuizPage;
import com.quizyourskill.app.data.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "result")
public class Result extends AbstractEntity{
   @NotNull
   private Integer attempts;

   private LocalDateTime timeStart;

   private LocalDateTime timeEnd;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private User user;

   @ManyToOne
   @JoinColumn(name = "course_id", nullable = false)
   private Course course;

   @ManyToOne
   @JoinColumn(name = "quiz_id", nullable = false)
   private Quiz quiz;

   @ManyToOne
   @JoinColumn(name = "quiz_page_id", nullable = false)
   private QuizPage quizPage;

   @ManyToOne
   @JoinColumn(name = "answer_option_id", nullable = false)
   private AnswerOption answerOption;
}