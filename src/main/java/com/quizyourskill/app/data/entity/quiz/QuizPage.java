package com.quizyourskill.app.data.entity.quiz;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.Details;
import com.quizyourskill.app.data.entity.result.Result;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "quiz_page")
public class QuizPage extends AbstractEntity{
   @NotNull
   private Boolean isFirstPage;

   @NotNull
   private Boolean isLastPage;

   @NotNull
   private Boolean isRandomOrder;

   @NotNull
   private Integer pageNumber;

   @NotNull
   private String title;

   @NotNull
   @Column(columnDefinition = "TEXT")
   private String text;

   @ManyToOne
   @JoinColumn(name = "quiz_id", nullable = false)
   private Quiz quiz;

   @ManyToOne
   @JoinColumn(name = "details_id")
   private Details details;

   @OneToMany(mappedBy = "quizPage", fetch = FetchType.EAGER)
   private Set<Question> questions;

   @OneToMany(mappedBy = "quizPage")
   private Set<Result> results;

   public Boolean isFirstPage(){
      return isFirstPage;
   }

   public Boolean isLastPage(){
      return isLastPage;
   }

   public String getTitle(){
      return title;
   }

   public Integer getPageNumber(){
      return pageNumber;
   }

   public Details getDetails(){
      return details;
   }

   public Set<Question> getQuestions(){
      return questions;
   }
}