package com.quizyourskill.app.data.service.learn;

import com.quizyourskill.app.data.entity.learn.LearningUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface LearningUnitRepository extends JpaRepository<LearningUnit, Integer>{
   Optional<Set<LearningUnit>> findAllByCourseId(Integer courseId);
}