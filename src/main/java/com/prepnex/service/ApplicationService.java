package com.prepnex.service;

import com.prepnex.model.Application;
import com.prepnex.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application updateApplication(Long id, Application updated) {
        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        existing.setCompanyName(updated.getCompanyName());
        existing.setRole(updated.getRole());
        existing.setStatus(updated.getStatus());
        existing.setNotes(updated.getNotes());
        existing.setAppliedDate(updated.getAppliedDate());
        return applicationRepository.save(existing);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}