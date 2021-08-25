package com.vmo.freshermanagement.repository;

import com.vmo.freshermanagement.model.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Integer> {
    List<Fresher> findAllByNameContainingIgnoreCase(String name);

    Optional<Fresher> findFresherByEmail(String name);

    List<Fresher> findAllByProgrammingLanguage_Id(int id);

    List<Fresher> findAll();

    int countFresherByCenter_Id(int id);

    boolean existsByEmail(String email);
}
