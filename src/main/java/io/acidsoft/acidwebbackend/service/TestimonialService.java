package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.entity.Testimonial;
import io.acidsoft.acidwebbackend.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    @Transactional(readOnly = true)
    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAllByOrderByOrderIndexAsc();
    }

    @Transactional
    public Testimonial saveTestimonial(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }
}
