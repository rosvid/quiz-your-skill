package com.quizyourskill.app.data.service.learn;

import com.quizyourskill.app.data.entity.learn.LearningUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class LearningUnitService{
   private final LearningUnitRepository learningUnitRepository;

   @Autowired
   public LearningUnitService(LearningUnitRepository learningUnitRepository){
      this.learningUnitRepository = learningUnitRepository;
   }

   public Optional<LearningUnit> findById(Integer id){
      return learningUnitRepository.findById(id);
   }

   public Optional<Set<LearningUnit>> findAllByCourseId(Integer courseId){
      return learningUnitRepository.findAllByCourseId(courseId);
   }
}