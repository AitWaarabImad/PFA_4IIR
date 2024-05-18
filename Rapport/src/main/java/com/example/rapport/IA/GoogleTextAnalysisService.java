package com.example.rapport.IA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class GoogleTextAnalysisService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleTextAnalysisService() {
        this.restTemplate = new RestTemplate();
    }

    public String summarizeText(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"document\":{\"type\":\"PLAIN_TEXT\",\"content\":\"" + text + "\"},\"features\":{\"extractSyntax\":false,\"extractEntities\":true,\"extractDocumentSentiment\":false,\"extractEntitySentiment\":false,\"classifyText\":false},\"encodingType\":\"UTF8\"}";
        String requestUrl = "https://language.googleapis.com/v1/documents:analyzeEntities?key=" + apiKey;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, entity, String.class);

        return response.getBody(); // Analyser et formater le résumé à partir de la réponse
    }
}
