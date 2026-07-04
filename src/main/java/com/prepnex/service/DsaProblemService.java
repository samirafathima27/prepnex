package com.prepnex.service;

import com.prepnex.model.DsaProblem;
import com.prepnex.model.User;
import com.prepnex.repository.DsaProblemRepository;
import com.prepnex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DsaProblemService {

    private final DsaProblemRepository dsaProblemRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public DsaProblem addProblem(DsaProblem problem) {
        problem.setUser(getCurrentUser());
        return dsaProblemRepository.save(problem);
    }

    public List<DsaProblem> getAllProblems() {
        return dsaProblemRepository.findByUser(getCurrentUser());
    }

    public DsaProblem updateProblem(Long id, DsaProblem updated) {
        DsaProblem existing = dsaProblemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        existing.setTopic(updated.getTopic());
        existing.setDifficulty(updated.getDifficulty());
        existing.setPlatform(updated.getPlatform());
        existing.setProblemsSolved(updated.getProblemsSolved());
        existing.setLastRevisedDate(updated.getLastRevisedDate());
        return dsaProblemRepository.save(existing);
    }

    public void deleteProblem(Long id) {
        dsaProblemRepository.deleteById(id);
    }
}