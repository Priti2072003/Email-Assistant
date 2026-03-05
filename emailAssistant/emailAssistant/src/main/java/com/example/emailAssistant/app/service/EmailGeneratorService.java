package com.example.emailAssistant.app.service;

import com.example.emailAssistant.app.dto.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<String> generateReply(EmailRequest emailRequest) {

        String prompt = buildPrompt(emailRequest);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );
 String fullUrl =geminiApiUrl+"?key=" + geminiApiKey;
        return webClient.post()
                .uri(fullUrl)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractResponseContent);
    }

    private String extractResponseContent(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode textNode = root.at("/candidates/0/content/parts/0/text");

            if (textNode.isMissingNode()) {
                return "❌ No text returned by Gemini.\nRAW:\n" + json;
            }

            return textNode.asText();

        } catch (Exception e) {
            return "❌ Parsing failed: " + e.getMessage();
        }
    }



    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email. ");
        prompt.append("Do not generate a subject line. ");

        if (emailRequest.getTone() != null && !emailRequest.getTone().isBlank()) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }

        prompt.append("\n\nOriginal email:\n");
        prompt.append(emailRequest.getEmailContent());

        return prompt.toString();
    }
}


