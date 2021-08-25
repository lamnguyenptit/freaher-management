package com.vmo.freshermanagement.repository;

import com.vmo.freshermanagement.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findAllByFresher_Id(int fresherId);

    Result findByFresher_IdAndAndExercise_Id(int fresherId, int ExerciseId);
}
