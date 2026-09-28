package io.acidsoft.acidwebbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CareerVacancyRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Job type is required")
    private String jobType;

    @NotBlank(message = "Description is required")
    private String description;

    private Boolean isActive = true;
}
