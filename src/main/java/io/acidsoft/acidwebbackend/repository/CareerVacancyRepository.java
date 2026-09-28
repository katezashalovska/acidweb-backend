package io.acidsoft.acidwebbackend.repository;

import io.acidsoft.acidwebbackend.entity.CareerVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerVacancyRepository extends JpaRepository<CareerVacancy, Long> {
    List<CareerVacancy> findByIsActiveTrueOrderByCreatedAtDesc();
}
