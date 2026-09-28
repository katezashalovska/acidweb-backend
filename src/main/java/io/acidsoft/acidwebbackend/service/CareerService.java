package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.dto.request.CareerVacancyRequest;
import io.acidsoft.acidwebbackend.entity.CareerVacancy;
import io.acidsoft.acidwebbackend.exception.ResourceNotFoundException;
import io.acidsoft.acidwebbackend.repository.CareerVacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerVacancyRepository vacancyRepository;

    @Transactional(readOnly = true)
    public List<CareerVacancy> getActiveVacancies() {
        return vacancyRepository.findByIsActiveTrueOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<CareerVacancy> getAllVacanciesForAdmin() {
        return vacancyRepository.findAll();
    }

    @Transactional
    public CareerVacancy createVacancy(CareerVacancyRequest request) {
        CareerVacancy vacancy = CareerVacancy.builder()
                .title(request.getTitle())
                .department(request.getDepartment())
                .jobType(request.getJobType())
                .description(request.getDescription())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        return vacancyRepository.save(vacancy);
    }

    @Transactional
    public CareerVacancy updateVacancy(Long id, CareerVacancyRequest request) {
        CareerVacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CareerVacancy", "id", id));

        vacancy.setTitle(request.getTitle());
        vacancy.setDepartment(request.getDepartment());
        vacancy.setJobType(request.getJobType());
        vacancy.setDescription(request.getDescription());
        if (request.getIsActive() != null) {
            vacancy.setIsActive(request.getIsActive());
        }

        return vacancyRepository.save(vacancy);
    }

    @Transactional
    public void deleteVacancy(Long id) {
        if (!vacancyRepository.existsById(id)) {
            throw new ResourceNotFoundException("CareerVacancy", "id", id);
        }
        vacancyRepository.deleteById(id);
    }
}
