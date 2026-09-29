package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.dto.request.PortfolioCaseRequest;
import io.acidsoft.acidwebbackend.entity.PortfolioCase;
import io.acidsoft.acidwebbackend.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    // Public endpoints
    @GetMapping("/portfolio")
    public ResponseEntity<ApiResponse<List<PortfolioCase>>> getFeaturedCases() {
        List<PortfolioCase> cases = portfolioService.getFeaturedCases();
        return ResponseEntity.ok(ApiResponse.success(cases));
    }

    @GetMapping("/portfolio/{slug}")
    public ResponseEntity<ApiResponse<PortfolioCase>> getCaseBySlug(@PathVariable String slug) {
        PortfolioCase portfolioCase = portfolioService.getCaseBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success(portfolioCase));
    }

    // Admin endpoints
    @GetMapping("/admin/portfolio")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<PortfolioCase>>> getAllCasesForAdmin() {
        List<PortfolioCase> cases = portfolioService.getAllCasesForAdmin();
        return ResponseEntity.ok(ApiResponse.success(cases));
    }

    @PostMapping("/admin/portfolio")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PortfolioCase>> createCase(@Valid @RequestBody PortfolioCaseRequest request) {
        PortfolioCase created = portfolioService.createCase(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Portfolio case created", created));
    }

    @PutMapping("/admin/portfolio/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PortfolioCase>> updateCase(@PathVariable Long id, @Valid @RequestBody PortfolioCaseRequest request) {
        PortfolioCase updated = portfolioService.updateCase(id, request);
        return ResponseEntity.ok(ApiResponse.success("Portfolio case updated", updated));
    }

    @DeleteMapping("/admin/portfolio/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteCase(@PathVariable Long id) {
        portfolioService.deleteCase(id);
        return ResponseEntity.ok(ApiResponse.success("Portfolio case deleted", null));
    }
}
