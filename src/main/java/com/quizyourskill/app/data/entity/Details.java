package com.quizyourskill.app.data.entity;

import com.quizyourskill.app.data.entity.files.MediaFile;
import com.quizyourskill.app.data.entity.learn.LearningPage;
import com.quizyourskill.app.data.entity.quiz.QuizPage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "details")
public class Details extends AbstractEntity{
   @NotNull
   @Column(columnDefinition = "TEXT")
   private String text;

   @ManyToOne
   @JoinColumn(name = "media_file_id")
   private MediaFile mediaFile;

   @OneToMany(mappedBy = "details")
   private Set<LearningPage> learningPages;

   @OneToMany(mappedBy = "details")
   private Set<QuizPage> quizPages;

   public String getText(){
      return text;
   }
}