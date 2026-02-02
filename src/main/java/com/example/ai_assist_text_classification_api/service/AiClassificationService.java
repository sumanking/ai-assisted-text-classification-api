package com.example.ai_assist_text_classification_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiClassificationService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${ai.api.key}")
    private String apiKey;

    public AiClassificationService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public String classify(String text) {
        try {
            String response = webClient.post()
                    .uri("https://api.groq.com/openai/v1/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue("""
                        {
                          "model": "llama-3.1-8b-instant",
                          "messages": [
                            {
                              "role": "system",
                              "content": "You are a strict text classifier.\\n\\nCategories:\\n- Complaint: problems, issues, dissatisfaction.\\n- Query: questions or requests for information.\\n- Feedback: explicit praise or opinion about a product or service.\\n- Other: greetings, small talk, neutral statements, or anything that does not fit above.\\n\\nIMPORTANT: Greetings like 'hello', 'hi', 'hey' MUST be classified as Other.\\nReply with ONLY ONE WORD exactly from: Complaint, Query, Feedback, Other."
                            },
                            {
                              "role": "user",
                              "content": "%s"
                            }
                          ],
                          "temperature": 0,
                          "max_tokens": 5
                        }
                        """.formatted(text))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("RAW AI RESPONSE = " + response);

            JsonNode root = objectMapper.readTree(response);
            String result = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText()
                    .trim()
                    .replaceAll("[^A-Za-z]", "");

            // Final safety validation
            return switch (result) {
                case "Complaint", "Query", "Feedback", "Other" -> result;
                default -> "Other";
            };

        } catch (Exception e) {
            // If AI fails, do NOT guess — return neutral
            return "Other";
        }
    }
}
