package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.entity.PortfolioCase;
import io.acidsoft.acidwebbackend.exception.ResourceNotFoundException;
import io.acidsoft.acidwebbackend.repository.PortfolioCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioCaseRepository portfolioCaseRepository;

    @Transactional(readOnly = true)
    public List<PortfolioCase> getFeaturedCases() {
        return portfolioCaseRepository.findByIsFeaturedTrueOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public PortfolioCase getCaseBySlug(String slug) {
        return portfolioCaseRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("PortfolioCase", "slug", slug));
    }

    @Transactional
    public PortfolioCase saveCase(PortfolioCase portfolioCase) {
        return portfolioCaseRepository.save(portfolioCase);
    }

    @Transactional
    public void deleteCase(Long id) {
        if (!portfolioCaseRepository.existsById(id)) {
            throw new ResourceNotFoundException("PortfolioCase", "id", id);
        }
        portfolioCaseRepository.deleteById(id);
    }
}
