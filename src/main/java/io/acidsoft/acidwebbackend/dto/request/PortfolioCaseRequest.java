package io.acidsoft.acidwebbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class PortfolioCaseRequest {
    @NotBlank(message = "Slug is required")
    private String slug;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Category is required")
    private String category;

    private String metric;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Tech stack is required")
    private String techStack;

    private String appStoreUrl;

    private String googlePlayUrl;

    private Boolean isFeatured = false;

    private UUID imageId;

    private String imageAlt;

    private String problem;

    private String whatWeBuilt;

    private String hardPart;

    private String behanceUrl;
}
