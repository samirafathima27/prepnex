package com.prepnex.controller;

import com.prepnex.model.DsaProblem;
import com.prepnex.service.DsaProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/dsa")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DsaProblemController {

    private final DsaProblemService dsaProblemService;

    @PostMapping
    public ResponseEntity<DsaProblem> addProblem(@Valid @RequestBody DsaProblem problem) {
        return ResponseEntity.ok(dsaProblemService.addProblem(problem));
    }

    @GetMapping
    public ResponseEntity<List<DsaProblem>> getAllProblems() {
        return ResponseEntity.ok(dsaProblemService.getAllProblems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DsaProblem> updateProblem(@PathVariable Long id, @Valid @RequestBody DsaProblem problem) {
        return ResponseEntity.ok(dsaProblemService.updateProblem(id, problem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        dsaProblemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }
}