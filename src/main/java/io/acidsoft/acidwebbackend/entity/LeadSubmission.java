package io.acidsoft.acidwebbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lead_submissions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lead_type", nullable = false, length = 50)
    private String leadType; // CONTACT, FREE_AUDIT, JOB_APPLICATION

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "project_type", length = 100)
    private String projectType;

    @Column(length = 50)
    private String budget;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "app_store_url", length = 500)
    private String appStoreUrl;

    @Column(name = "google_play_url", length = 500)
    private String googlePlayUrl;

    @Column(name = "portfolio_url", length = 500)
    private String portfolioUrl;

    @Column(length = 30)
    private String status; // NEW, CONTACTED, CLOSED

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "NEW";
        }
    }
}
