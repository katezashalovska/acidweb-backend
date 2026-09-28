package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.entity.PortfolioCase;
import io.acidsoft.acidwebbackend.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

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
}
