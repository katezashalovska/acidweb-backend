package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.entity.Testimonial;
import io.acidsoft.acidwebbackend.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @GetMapping("/testimonials")
    public ResponseEntity<ApiResponse<List<Testimonial>>> getTestimonials() {
        List<Testimonial> testimonials = testimonialService.getAllTestimonials();
        return ResponseEntity.ok(ApiResponse.success(testimonials));
    }
}
