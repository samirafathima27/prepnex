package com.prepnex.service;

import com.prepnex.model.MockInterview;
import com.prepnex.model.User;
import com.prepnex.repository.MockInterviewRepository;
import com.prepnex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MockInterviewService {

    private final MockInterviewRepository mockInterviewRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public MockInterview addInterview(MockInterview interview) {
        interview.setUser(getCurrentUser());
        return mockInterviewRepository.save(interview);
    }

    public List<MockInterview> getAllInterviews() {
        return mockInterviewRepository.findByUser(getCurrentUser());
    }

    public MockInterview updateInterview(Long id, MockInterview updated) {
        MockInterview existing = mockInterviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        existing.setCompany(updated.getCompany());
        existing.setRoundType(updated.getRoundType());
        existing.setWentWell(updated.getWentWell());
        existing.setWentBadly(updated.getWentBadly());
        existing.setDate(updated.getDate());
        return mockInterviewRepository.save(existing);
    }

    public void deleteInterview(Long id) {
        mockInterviewRepository.deleteById(id);
    }
}