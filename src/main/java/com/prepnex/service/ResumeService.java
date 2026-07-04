package com.prepnex.service;

import com.prepnex.model.Resume;
import com.prepnex.model.User;
import com.prepnex.repository.ResumeRepository;
import com.prepnex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Resume addResume(Resume resume) {
        resume.setUser(getCurrentUser());
        return resumeRepository.save(resume);
    }

    public List<Resume> getAllResumes() {
        return resumeRepository.findByUser(getCurrentUser());
    }

    public Resume updateResume(Long id, Resume updated) {
        Resume existing = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        existing.setVersionName(updated.getVersionName());
        existing.setFileLink(updated.getFileLink());
        existing.setCompanySentTo(updated.getCompanySentTo());
        existing.setUploadDate(updated.getUploadDate());
        return resumeRepository.save(existing);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}