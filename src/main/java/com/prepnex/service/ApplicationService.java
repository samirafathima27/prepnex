package com.prepnex.service;

import com.prepnex.model.Application;
import com.prepnex.model.User;
import com.prepnex.repository.ApplicationRepository;
import com.prepnex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Application addApplication(Application application) {
        application.setUser(getCurrentUser());
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findByUser(getCurrentUser());
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