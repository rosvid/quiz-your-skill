package com.quizyourskill.app.data.service.course;

import com.quizyourskill.app.data.entity.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService{
   private final CourseRepository courseRepository;

   @Autowired
   public CourseService(CourseRepository courseRepository){
      this.courseRepository = courseRepository;
   }

   public Optional<Course> findById(Integer id){
      return courseRepository.findById(id);
   }

   public Optional<List<Course>> findAllByUserId(Integer userId){
      return courseRepository.findAllByUsersId(userId);
   }
}