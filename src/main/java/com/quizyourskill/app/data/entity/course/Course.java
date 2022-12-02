package com.quizyourskill.app.data.entity.course;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.learn.LearningUnit;
import com.quizyourskill.app.data.entity.quiz.Quiz;
import com.quizyourskill.app.data.entity.result.Result;
import com.quizyourskill.app.data.entity.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course extends AbstractEntity{
   @NotNull
   private String title;

   @NotNull
   private String description;

   @CreationTimestamp
   @NotNull
   private LocalDateTime creationDate;

   @ManyToMany(mappedBy = "courses")
   private Set<User> users;

   @ManyToOne
   @JoinColumn(name = "instructor_user_id", nullable = false)
   private User instructor;

   @OneToMany(mappedBy = "course")
   private Set<LearningUnit> learningUnits;

   @OneToMany(mappedBy = "course")
   private Set<Quiz> quizzes;

   @OneToMany(mappedBy = "course")
   private Set<Result> results;

   public String getTitle(){
      return title;
   }

   public String getDescription(){
      return description;
   }
}