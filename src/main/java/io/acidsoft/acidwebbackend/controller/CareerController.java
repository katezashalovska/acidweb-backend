package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.dto.request.CareerVacancyRequest;
import io.acidsoft.acidwebbackend.entity.CareerVacancy;
import io.acidsoft.acidwebbackend.service.CareerService;
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
public class CareerController {

    private final CareerService careerService;

    @GetMapping("/careers")
    public ResponseEntity<ApiResponse<List<CareerVacancy>>> getActiveVacancies() {
        List<CareerVacancy> vacancies = careerService.getActiveVacancies();
        return ResponseEntity.ok(ApiResponse.success(vacancies));
    }

    @GetMapping("/admin/careers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<CareerVacancy>>> getAllVacanciesForAdmin() {
        List<CareerVacancy> vacancies = careerService.getAllVacanciesForAdmin();
        return ResponseEntity.ok(ApiResponse.success(vacancies));
    }

    @PostMapping("/admin/careers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CareerVacancy>> createVacancy(@Valid @RequestBody CareerVacancyRequest request) {
        CareerVacancy created = careerService.createVacancy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Vacancy created", created));
    }

    @PutMapping("/admin/careers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CareerVacancy>> updateVacancy(@PathVariable Long id, @Valid @RequestBody CareerVacancyRequest request) {
        CareerVacancy updated = careerService.updateVacancy(id, request);
        return ResponseEntity.ok(ApiResponse.success("Vacancy updated", updated));
    }

    @DeleteMapping("/admin/careers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteVacancy(@PathVariable Long id) {
        careerService.deleteVacancy(id);
        return ResponseEntity.ok(ApiResponse.success("Vacancy deleted", null));
    }
}
