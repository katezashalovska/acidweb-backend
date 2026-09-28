package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.dto.request.LeadRequest;
import io.acidsoft.acidwebbackend.entity.LeadSubmission;
import io.acidsoft.acidwebbackend.exception.ResourceNotFoundException;
import io.acidsoft.acidwebbackend.repository.LeadSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadSubmissionRepository leadRepository;

    @Transactional
    public LeadSubmission submitLead(LeadRequest request) {
        // Honeypot anti-spam check
        if (StringUtils.hasText(request.getWebsite_hp())) {
            throw new IllegalArgumentException("Invalid submission format.");
        }

        LeadSubmission lead = LeadSubmission.builder()
                .leadType(request.getLeadType())
                .name(request.getName())
                .email(request.getEmail())
                .projectType(request.getProjectType())
                .budget(request.getBudget())
                .message(request.getMessage())
                .appStoreUrl(request.getAppStoreUrl())
                .googlePlayUrl(request.getGooglePlayUrl())
                .portfolioUrl(request.getPortfolioUrl())
                .status("NEW")
                .build();

        return leadRepository.save(lead);
    }

    @Transactional(readOnly = true)
    public List<LeadSubmission> getAllLeadsForAdmin() {
        return leadRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public LeadSubmission updateLeadStatus(Long id, String status) {
        LeadSubmission lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeadSubmission", "id", id));
        lead.setStatus(status);
        return leadRepository.save(lead);
    }

    @Transactional
    public void deleteLead(Long id) {
        if (!leadRepository.existsById(id)) {
            throw new ResourceNotFoundException("LeadSubmission", "id", id);
        }
        leadRepository.deleteById(id);
    }
}
