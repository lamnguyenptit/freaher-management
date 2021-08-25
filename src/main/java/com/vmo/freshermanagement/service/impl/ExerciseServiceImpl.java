package com.vmo.freshermanagement.service.impl;

import com.vmo.freshermanagement.exception.ResourceNotFoundException;
import com.vmo.freshermanagement.model.Exercise;
import com.vmo.freshermanagement.repository.ExerciseRepository;
import com.vmo.freshermanagement.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise findExerciseById(int id){
        return exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài tập"));
    }
}
