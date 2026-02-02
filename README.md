
AI-Assisted Text Classification API


Overview

The AI-Assisted Text Classification API is a backend service built using Java and Spring Boot that classifies user-provided text into one of the following categories:

Complaint

Query

Feedback

Other

The classification is performed using a Large Language Model (LLM) via the Groq API, ensuring intelligent, context-aware categorization instead of rule-based keyword matching.

This project demonstrates how to design, integrate, and expose an AI-powered service using clean backend architecture and REST principles.

Features

RESTful API with a single POST endpoint

AI-driven text classification (no hard-coded rules)

Integration with Groq’s OpenAI-compatible API

Clear separation of controller and service layers

Graceful error handling with safe defaults

Easily testable using Postman or any REST client

Technology Stack

Language: Java 21

Framework: Spring Boot

Build Tool: Maven

AI Provider: Groq API (OpenAI-compatible)

HTTP Client: Spring WebClient

JSON Parsing: Jackson

Architecture Overview
Controller  →  Service  →  Groq AI API
   ↓             ↓
Request       AI Response
   ↓             ↓
JSON Input   Category Output

Key Design Principles

Business logic is isolated in the service layer

AI is responsible for all classification decisions

Backend only validates and normalizes AI output

No rule-based categorization in Java code

API Specification
Endpoint
POST /api/classify

Request Headers
Content-Type: application/json

Request Body
{
  "text": "Your input text here"
}

Response Body
{
  "category": "Complaint | Query | Feedback | Other"
}

Classification Logic (AI-Driven)

The AI model classifies text based on semantic meaning:

Category	Description
Complaint	Problems, issues, dissatisfaction
Query	Questions or requests for information
Feedback	Explicit praise or opinion
Other	Greetings, small talk, neutral messages

Examples:

"The app crashes when I pay" → Complaint

"How can I reset my password?" → Query

"Great app, works smoothly" → Feedback

"Hello there" → Other

Error Handling Strategy

If the AI API fails, times out, or returns unexpected output:

The service safely returns "Other"

No assumptions or rule-based guessing are applied

This guarantees API stability under all conditions

Environment Setup
Prerequisites

Java 21 installed

Maven installed

Groq API key

Configuration
application.properties
ai.api.key=My_GROQ_API_KEY


Note: Never commit real API keys to GitHub.
Use environment variables or local configuration for production.

Running the Application
mvn spring-boot:run


The application will start on:

http://localhost:8080

Testing the API
Using Postman

POST

http://localhost:8080/api/classify


Sample Requests

{ "text": "The app crashes every time I try to pay" }

{ "text": "How can I change my email address?" }

{ "text": "The new update is amazing, great work!" }

{ "text": "Hello there" }

Sample Responses
{ "category": "Complaint" }

{ "category": "Query" }

{ "category": "Feedback" }

{ "category": "Other" }

Why Groq?

Groq provides:

OpenAI-compatible APIs

Extremely fast inference

Stable free-tier usage

Simple integration with existing OpenAI-style requests

This makes it ideal for backend AI integrations.

Project Structure
src/main/java
 └── com.example.ai_assist_text_classification_api
     ├── controller
     │   └── ClassificationController.java
     ├── service
     │   └── AiClassificationService.java
     ├── dto
     │   ├── ClassificationRequest.java
     │   └── ClassificationResponse.java
     └── AiAssistTextClassificationApiApplication.java

Future Enhancements

Support batch text classification

Add confidence scores from AI output

Dockerize the application

Add authentication and rate limiting

Cache frequent classifications

Conclusion

This project demonstrates a clean and professional approach to integrating AI into backend services using Spring Boot.
It avoids rule-based shortcuts and instead leverages modern AI models for intelligent decision-making.

Author

Suman Acharyya
Backend Developer | Java | Spring Boot | AI Integration
