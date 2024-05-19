package com.example.rapport.IA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HuggingFaceTextAnalysisService {
    @Value("${huggingface.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public HuggingFaceTextAnalysisService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String correctText(String text) {
        return processText(text, "t5-small", "https://api-inference.huggingface.co/models/t5-small", "translation_text");
    }

    public String summarizeText(String text) {
        return processText(text, "facebook/bart-large-cnn", "https://api-inference.huggingface.co/models/facebook/bart-large-cnn", "summary_text");
    }

    private String processText(String text, String model, String endpoint, String responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = "{\"inputs\": \"" + text + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, entity, String.class);
            return parseResponse(response.getBody(), responseType);
        } catch (HttpServerErrorException e) {
            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                double estimatedTime = extractEstimatedTime(e.getResponseBodyAsString());
                try {
                    Thread.sleep((long) (estimatedTime * 1000) + 5000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                return processText(text, model, endpoint, responseType);
            } else {
                throw e;
            }
        }
    }

    private String parseResponse(String json, String responseType) {
        try {
            System.out.println("Complete Response from Hugging Face: " + json);
            JsonNode rootNode = objectMapper.readTree(json);

            if (rootNode.isArray() && rootNode.size() > 0) {
                JsonNode firstElement = rootNode.get(0);
                JsonNode resultNode = firstElement.path(responseType);
                if (resultNode.isMissingNode() || !resultNode.isTextual()) {
                    System.out.println("Failed to find result for " + responseType);
                    return "";
                }
                return resultNode.asText();
            } else {
                System.out.println("Response is not an array or is empty");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Error processing the response\"}";
        }
    }

    private double extractEstimatedTime(String json) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            return rootNode.path("estimated_time").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return 10.0;
        }
    }
}
