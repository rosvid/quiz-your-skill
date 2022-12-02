package com.quizyourskill.app.data.entity.learn;

import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.Details;
import com.quizyourskill.app.data.entity.files.MediaFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "learning_page")
public class LearningPage extends AbstractEntity{
   @NotNull
   private Boolean isFirstPage;

   @NotNull
   private Boolean isLastPage;

   @NotNull
   private Integer pageNumber;

   @NotNull
   private String title;

   @NotNull
   @Column(columnDefinition = "TEXT")
   private String text;

   @ManyToOne
   @JoinColumn(name = "learning_unit_id", nullable = false)
   private LearningUnit learningUnit;

   @ManyToOne
   @JoinColumn(name = "details_id")
   private Details details;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "learning_page_has_media_file",
           joinColumns = @JoinColumn(name = "learning_page_id"),
           inverseJoinColumns = @JoinColumn(name = "media_file_id"))
   private Set<MediaFile> mediaFiles;

   public Integer getPageNumber(){
      return pageNumber;
   }

   public String getTitle(){
      return title;
   }

   public String getText(){
      return text;
   }

   public Details getDetails(){
      return details;
   }

   public Set<MediaFile> getMediaFiles(){
      return mediaFiles;
   }

   public Boolean isFirstPage(){
      return isFirstPage;
   }

   public Boolean isLastPage(){
      return isLastPage;
   }
}