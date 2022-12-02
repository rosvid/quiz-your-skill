package com.quizyourskill.app.data.service.quiz;

import com.quizyourskill.app.data.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{
    Optional<Set<Quiz>> findAllByCourseId(Integer courseId);
}
