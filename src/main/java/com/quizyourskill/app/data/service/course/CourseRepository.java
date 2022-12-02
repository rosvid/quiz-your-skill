package com.quizyourskill.app.data.service.course;

import com.quizyourskill.app.data.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer>{
   Optional<List<Course>> findAllByUsersId(Integer id);
}