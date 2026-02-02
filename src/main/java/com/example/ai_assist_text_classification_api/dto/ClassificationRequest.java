package com.example.ai_assist_text_classification_api.dto;
import jakarta.validation.constraints.NotBlank;

public class ClassificationRequest {

    @NotBlank(message = "Text is required")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}