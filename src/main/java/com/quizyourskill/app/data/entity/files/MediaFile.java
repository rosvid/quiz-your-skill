package com.quizyourskill.app.data.entity.files;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.Details;
import com.quizyourskill.app.data.entity.learn.LearningPage;
import com.quizyourskill.app.data.entity.quiz.AnswerOption;
import com.quizyourskill.app.data.entity.quiz.Question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "media_file")
public class MediaFile extends AbstractEntity{
   @NotNull
   private String name;

   @NotNull
   private String path;

   @ManyToOne
   @JoinColumn(name = "file_type_id", nullable = false)
   private FileType fileType;

   @OneToMany(mappedBy = "mediaFile")
   private Set<Details> details;

   @OneToMany(mappedBy = "mediaFile")
   private Set<Question> questions;

   @OneToMany(mappedBy = "mediaFile")
   private Set<AnswerOption> answerOptions;

   @ManyToMany(mappedBy = "mediaFiles")
   private Set<LearningPage> learningPages;

   public String getName(){
      return name;
   }

   public String getPath(){
      return path;
   }
}