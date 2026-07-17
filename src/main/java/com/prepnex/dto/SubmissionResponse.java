package com.prepnex.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class SubmissionResponse {
    private String status;
    private String output;
    private String expectedOutput;
    private boolean correct;
    private String message;
    private List<Map<String, Object>> testResults;
    private String runtime;
}