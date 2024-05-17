package com.example.rapport.IA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeepAiService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DeepAiService.class);

    @Value("${deepai.api.key}")
    private String apiKey;

    public DeepAiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String summarizeText(String text) {
        return callApi("https://api.deepai.org/api/summarization", text);
    }

    public String correctText(String text) {
        return callApi("https://api.deepai.org/api/text-generator", text);
    }

    private String callApi(String apiUrl, String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        logger.info("API Key used: " + apiKey); // Log the API key being used for debugging

        String body = "{\"text\": \"" + text + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            logger.info("API Response: " + response.getBody());
            return parseResponse(response.getBody());
        } catch (RestClientException e) {
            logger.error("Error calling DeepAI API: " + e.getMessage());
            return "Error calling DeepAI API: " + e.getMessage();
        }
    }

    private String parseResponse(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            logger.info("Parsed Response: " + root.toString());
            JsonNode outputNode = root.path("output");
            if (!outputNode.isMissingNode()) {
                return outputNode.asText();
            }
            return "No output found in response.";
        } catch (Exception e) {
            logger.error("Error parsing response: " + e.getMessage());
            return "Error parsing response: " + e.getMessage();
        }
    }
}
