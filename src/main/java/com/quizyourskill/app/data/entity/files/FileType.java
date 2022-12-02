package com.quizyourskill.app.data.entity.files;

import com.quizyourskill.app.data.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "file_type")
public class FileType extends AbstractEntity{
   @NotNull
   private String description;

   @OneToMany(mappedBy = "fileType")
   private Set<MediaFile> mediaFiles;
}