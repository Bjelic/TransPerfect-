package service;

import model.TranslationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public interface TranslationService {

    String translateContent(TranslationRequest request);

    Set<String> getSupportedLanguages();

    Set<String> getSupportedDomains();

    String translate(TranslationRequest request);

    void validateRequest(TranslationRequest request);

    void setRestTemplate(RestTemplate restTemplate);
}
