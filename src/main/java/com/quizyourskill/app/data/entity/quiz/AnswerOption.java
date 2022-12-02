package com.quizyourskill.app.data.entity.quiz;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.files.MediaFile;
import com.quizyourskill.app.data.entity.result.Result;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "answer_option")
public class AnswerOption extends AbstractEntity{
   @NotNull
   private Boolean isCorrect;

   @NotNull
   private Integer position;

   @NotNull
   private String text;

   @ManyToOne
   @JoinColumn(name = "question_id", nullable = false)
   private Question question;

   @ManyToOne
   @JoinColumn(name = "media_file_id")
   private MediaFile mediaFile;

   @OneToMany(mappedBy = "answerOption")
   private Set<Result> results;

   public String getText(){
      return text;
   }
}