package io.acidsoft.acidwebbackend.repository;

import io.acidsoft.acidwebbackend.entity.PortfolioCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioCaseRepository extends JpaRepository<PortfolioCase, Long> {
    Optional<PortfolioCase> findBySlug(String slug);
    List<PortfolioCase> findByIsFeaturedTrueOrderByCreatedAtDesc();
}
