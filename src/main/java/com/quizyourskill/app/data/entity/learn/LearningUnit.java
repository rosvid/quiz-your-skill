package com.quizyourskill.app.data.entity.learn;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.course.Course;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "learning_unit")
public class LearningUnit extends AbstractEntity{
   @NotNull
   private String title;

   @CreationTimestamp
   @NotNull
   private LocalDateTime creationDate;

   @ManyToOne
   @JoinColumn(name = "course_id", nullable = false)
   private Course course;

   @OneToMany(mappedBy = "learningUnit", fetch = FetchType.EAGER)
   private List<LearningPage> learningPages;

   public String getTitle(){
      return title;
   }

   public List<LearningPage> getLearningPages(){
      return learningPages;
   }

   public Course getCourse(){
      return course;
   }
}