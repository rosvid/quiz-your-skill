package com.quizyourskill.app.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quizyourskill.app.data.entity.AbstractEntity;
import com.quizyourskill.app.data.entity.course.Course;
import com.quizyourskill.app.data.entity.result.Result;
import javax.annotation.Nonnull;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
public class User extends AbstractEntity{
   @NotNull
   private String username;

   @NotNull
   private String name;

   @JsonIgnore
   private String password;

   @CollectionTable(name = "user_has_role", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "role_name", nullable = false)
   @Enumerated(EnumType.STRING)
   @ElementCollection(fetch = FetchType.EAGER)
   @Nonnull
   private Set<Role> roles;

   @ManyToMany
   @JoinTable(name = "user_attends_course",
         joinColumns = @JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "course_id"))
   private Set<Course> courses;

   @OneToMany(mappedBy = "instructor")
   private Set<Course> coursesAsInstructor;

   @OneToMany(mappedBy = "user")
   private Set<Result> results;

   public String getUsername(){
      return username;
   }

   public void setUsername(String username){
      this.username = username;
   }

   public String getName(){
      return name;
   }

   public void setName(String name){
      this.name = name;
   }

   public String getPassword(){
      return password;
   }

   public void setPassword(String hashedPassword){
      this.password = hashedPassword;
   }

   public Set<Role> getRoles(){
      return roles;
   }

   public void setRoles(Set<Role> roles){
      this.roles = roles;
   }
}