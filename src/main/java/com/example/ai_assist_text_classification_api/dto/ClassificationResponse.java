package com.example.ai_assist_text_classification_api.dto;
public class ClassificationResponse {

    private String category;

    public ClassificationResponse(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}