package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.dto.request.LeadRequest;
import io.acidsoft.acidwebbackend.entity.LeadSubmission;
import io.acidsoft.acidwebbackend.service.LeadService;
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
public class LeadController {

    private final LeadService leadService;

    @PostMapping("/leads")
    public ResponseEntity<ApiResponse<LeadSubmission>> submitLead(@Valid @RequestBody LeadRequest request) {
        LeadSubmission lead = leadService.submitLead(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Thank you! Your submission has been received.", lead));
    }

    @GetMapping("/admin/leads")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<LeadSubmission>>> getAllLeads() {
        List<LeadSubmission> leads = leadService.getAllLeadsForAdmin();
        return ResponseEntity.ok(ApiResponse.success(leads));
    }

    @PatchMapping("/admin/leads/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<LeadSubmission>> updateLeadStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        LeadSubmission updated = leadService.updateLeadStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Lead status updated", updated));
    }

    @DeleteMapping("/admin/leads/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.ok(ApiResponse.success("Lead deleted", null));
    }
}
