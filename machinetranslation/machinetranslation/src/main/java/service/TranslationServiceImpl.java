package service;


import model.TranslationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${mt.api.url}")
    private String apiUrl;

    private static final int MAX_WORDS = 30;

    @Override
    public String translateContent(TranslationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TranslationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(apiUrl + "/translate", HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error while translating content: " + e.getMessage());
        }

        return response.getBody();
    }

    @Override
    public Set<String> getSupportedLanguages() {
        ResponseEntity<Set> response = restTemplate.getForEntity(apiUrl + "/languages", Set.class);
        return response.getBody();
    }

    @Override
    public Set<String> getSupportedDomains() {
        ResponseEntity<Set> response = restTemplate.getForEntity(apiUrl + "/domains", Set.class);
        return response.getBody();
    }

    @Override
    public String translate(TranslationRequest request) {
        if (request.getContent().split("\\s+").length > 30) {
            throw new IllegalArgumentException("Content exceeds 30 words limit.");
        }
        return restTemplate.postForObject(apiUrl + "translate", request, String.class);
    }

    @Override
    public void validateRequest(TranslationRequest request) {
        if (request.getSourceLanguage() == null || request.getTargetLanguage() == null ||
                request.getDomain() == null || request.getContent() == null ||
                request.getContent().split("\\s+").length > MAX_WORDS) {
            throw new IllegalArgumentException("Invalid request parameters");
        }
    }

}
