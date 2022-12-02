package com.quizyourskill.app.data.entity.quiz;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.files.MediaFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question extends AbstractEntity{
   @NotNull
   private Boolean areAnswerOptionsInRandomOrder;

   @NotNull
   private String text;

   @NotNull
   private Boolean isMultipleChoice;

   @ManyToOne
   @JoinColumn(name = "quiz_page_id", nullable = false)
   private QuizPage quizPage;

   @ManyToOne
   @JoinColumn(name = "media_file_id")
   private MediaFile mediaFile;

   @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
   private Set<AnswerOption> answerOptions;

   public String getText(){
      return text;
   }

   public Set<AnswerOption> getAnswerOptions(){
      return answerOptions;
   }

   public MediaFile getMediaFile(){
      return mediaFile;
   }
}