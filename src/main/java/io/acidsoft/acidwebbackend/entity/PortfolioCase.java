package io.acidsoft.acidwebbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "portfolio_cases")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(length = 100)
    private String metric;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "tech_stack", nullable = false, columnDefinition = "TEXT")
    private String techStack;

    @Column(name = "app_store_url", length = 500)
    private String appStoreUrl;

    @Column(name = "google_play_url", length = 500)
    private String googlePlayUrl;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.isFeatured == null) {
            this.isFeatured = true;
        }
    }
}
