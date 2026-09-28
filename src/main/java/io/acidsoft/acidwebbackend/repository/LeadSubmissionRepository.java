package io.acidsoft.acidwebbackend.repository;

import io.acidsoft.acidwebbackend.entity.LeadSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadSubmissionRepository extends JpaRepository<LeadSubmission, Long> {
    List<LeadSubmission> findAllByOrderByCreatedAtDesc();
    List<LeadSubmission> findByStatusOrderByCreatedAtDesc(String status);
}
