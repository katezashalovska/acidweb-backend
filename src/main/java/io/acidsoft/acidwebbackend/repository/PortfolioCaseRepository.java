package io.acidsoft.acidwebbackend.repository;

import io.acidsoft.acidwebbackend.entity.PortfolioCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PortfolioCaseRepository extends JpaRepository<PortfolioCase, Long> {
    Optional<PortfolioCase> findBySlug(String slug);
    List<PortfolioCase> findAllByOrderByCreatedAtDesc();
    boolean existsByImageId(UUID imageId);
}
