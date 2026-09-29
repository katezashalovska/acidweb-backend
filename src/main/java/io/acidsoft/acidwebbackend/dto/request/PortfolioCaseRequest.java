package io.acidsoft.acidwebbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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

    private Boolean isFeatured = true;
}
