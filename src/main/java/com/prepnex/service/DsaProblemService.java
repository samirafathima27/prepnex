package com.prepnex.service;

import com.prepnex.model.DsaProblem;
import com.prepnex.repository.DsaProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DsaProblemService {

    private final DsaProblemRepository dsaProblemRepository;

    public DsaProblem addProblem(DsaProblem problem) {
        return dsaProblemRepository.save(problem);
    }

    public List<DsaProblem> getAllProblems() {
        return dsaProblemRepository.findAll();
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