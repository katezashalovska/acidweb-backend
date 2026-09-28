package io.acidsoft.acidwebbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeadRequest {
    @NotBlank(message = "Lead type is required")
    private String leadType; // CONTACT, FREE_AUDIT, JOB_APPLICATION

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String projectType;
    private String budget;
    private String message;
    private String appStoreUrl;
    private String googlePlayUrl;
    private String portfolioUrl;

    // Honeypot field (must be empty for valid human submission)
    private String website_hp;
}
