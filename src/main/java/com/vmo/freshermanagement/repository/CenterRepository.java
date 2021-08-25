package com.vmo.freshermanagement.repository;

import com.vmo.freshermanagement.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {
    Optional<Center> findById(int id);
}
