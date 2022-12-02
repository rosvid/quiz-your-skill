package com.quizyourskill.app.data.entity.quiz;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.course.Course;
import com.quizyourskill.app.data.entity.result.Result;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz extends AbstractEntity{
   @CreationTimestamp
   @NotNull
   private LocalDateTime creationDate;

   @NotNull
   private Boolean isRandomOrder;

   @NotNull
   private String title;

   @ManyToOne
   @JoinColumn(name = "course_id", nullable = false)
   private Course course;

   @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
   private Set<QuizPage> quizPages;

   @OneToMany(mappedBy = "quiz")
   private Set<Result> results;

   public String getTitle(){
      return title;
   }

   public Set<QuizPage> getQuizPages(){
      return quizPages;
   }

   public Course getCourse(){
      return course;
   }
}