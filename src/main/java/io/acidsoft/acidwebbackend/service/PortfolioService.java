package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.dto.request.PortfolioCaseRequest;
import io.acidsoft.acidwebbackend.entity.PortfolioCase;
import io.acidsoft.acidwebbackend.exception.ResourceNotFoundException;
import io.acidsoft.acidwebbackend.repository.PortfolioCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioCaseRepository portfolioCaseRepository;
    private final MediaFileService mediaFileService;

    @Transactional(readOnly = true)
    public List<PortfolioCase> getPublicCases() {
        return portfolioCaseRepository.findAllByOrderByCreatedAtDesc();
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
        mediaFileService.requireMediaFile(request.getImageId());
        PortfolioCase portfolioCase = PortfolioCase.builder()
                .slug(request.getSlug())
                .title(request.getTitle())
                .category(request.getCategory())
                .metric(request.getMetric())
                .description(request.getDescription())
                .techStack(request.getTechStack())
                .appStoreUrl(request.getAppStoreUrl())
                .googlePlayUrl(request.getGooglePlayUrl())
                .isFeatured(request.getIsFeatured() != null ? request.getIsFeatured() : false)
                .imageId(request.getImageId())
                .imageAlt(request.getImageAlt())
                .problem(request.getProblem())
                .whatWeBuilt(request.getWhatWeBuilt())
                .hardPart(request.getHardPart())
                .behanceUrl(request.getBehanceUrl())
                .build();
        return portfolioCaseRepository.save(portfolioCase);
    }

    @Transactional
    public PortfolioCase updateCase(Long id, PortfolioCaseRequest request) {
        PortfolioCase portfolioCase = portfolioCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PortfolioCase", "id", id));

        UUID previousImageId = portfolioCase.getImageId();
        mediaFileService.requireMediaFile(request.getImageId());

        portfolioCase.setSlug(request.getSlug());
        portfolioCase.setTitle(request.getTitle());
        portfolioCase.setCategory(request.getCategory());
        portfolioCase.setMetric(request.getMetric());
        portfolioCase.setDescription(request.getDescription());
        portfolioCase.setTechStack(request.getTechStack());
        portfolioCase.setAppStoreUrl(request.getAppStoreUrl());
        portfolioCase.setGooglePlayUrl(request.getGooglePlayUrl());
        portfolioCase.setImageId(request.getImageId());
        portfolioCase.setImageAlt(request.getImageAlt());
        portfolioCase.setProblem(request.getProblem());
        portfolioCase.setWhatWeBuilt(request.getWhatWeBuilt());
        portfolioCase.setHardPart(request.getHardPart());
        portfolioCase.setBehanceUrl(request.getBehanceUrl());
        if (request.getIsFeatured() != null) {
            portfolioCase.setIsFeatured(request.getIsFeatured());
        }

        PortfolioCase saved = portfolioCaseRepository.save(portfolioCase);
        if (!Objects.equals(previousImageId, saved.getImageId())) {
            mediaFileService.deleteIfUnused(previousImageId);
        }
        return saved;
    }

    @Transactional
    public PortfolioCase saveCase(PortfolioCase portfolioCase) {
        return portfolioCaseRepository.save(portfolioCase);
    }

    @Transactional
    public void deleteCase(Long id) {
        PortfolioCase portfolioCase = portfolioCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PortfolioCase", "id", id));
        UUID imageId = portfolioCase.getImageId();
        portfolioCaseRepository.delete(portfolioCase);
        portfolioCaseRepository.flush();
        mediaFileService.deleteIfUnused(imageId);
    }
}
