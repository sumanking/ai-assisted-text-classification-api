package com.example.ai_assist_text_classification_api.controller;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.*;

import com.example.ai_assist_text_classification_api.dto.ClassificationRequest;
import com.example.ai_assist_text_classification_api.dto.ClassificationResponse;
import com.example.ai_assist_text_classification_api.service.AiClassificationService;

@RestController
@RequestMapping("/api")
public class ClassificationController {

    private final AiClassificationService service;

    public ClassificationController(AiClassificationService service) {
        this.service = service;
    }

    @PostMapping("/classify")
    public ClassificationResponse classifyText(
            @Valid @RequestBody ClassificationRequest request) {

        String category = service.classify(request.getText());
        return new ClassificationResponse(category);
    }
}