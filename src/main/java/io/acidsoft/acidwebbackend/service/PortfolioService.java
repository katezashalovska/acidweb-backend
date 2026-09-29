package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.dto.request.PortfolioCaseRequest;
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

    @Transactional(readOnly = true)
    public List<PortfolioCase> getAllCasesForAdmin() {
        return portfolioCaseRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public PortfolioCase createCase(PortfolioCaseRequest request) {
        PortfolioCase portfolioCase = PortfolioCase.builder()
                .slug(request.getSlug())
                .title(request.getTitle())
                .category(request.getCategory())
                .metric(request.getMetric())
                .description(request.getDescription())
                .techStack(request.getTechStack())
                .appStoreUrl(request.getAppStoreUrl())
                .googlePlayUrl(request.getGooglePlayUrl())
                .isFeatured(request.getIsFeatured() != null ? request.getIsFeatured() : true)
                .build();
        return portfolioCaseRepository.save(portfolioCase);
    }

    @Transactional
    public PortfolioCase updateCase(Long id, PortfolioCaseRequest request) {
        PortfolioCase portfolioCase = portfolioCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PortfolioCase", "id", id));

        portfolioCase.setSlug(request.getSlug());
        portfolioCase.setTitle(request.getTitle());
        portfolioCase.setCategory(request.getCategory());
        portfolioCase.setMetric(request.getMetric());
        portfolioCase.setDescription(request.getDescription());
        portfolioCase.setTechStack(request.getTechStack());
        portfolioCase.setAppStoreUrl(request.getAppStoreUrl());
        portfolioCase.setGooglePlayUrl(request.getGooglePlayUrl());
        if (request.getIsFeatured() != null) {
            portfolioCase.setIsFeatured(request.getIsFeatured());
        }

        return portfolioCaseRepository.save(portfolioCase);
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
