package controller;

import model.TranslationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TranslationService;

import java.util.Set;


@RestController
@RequestMapping("/api/translate")
public class TranslationController {
    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/languages")
    public ResponseEntity<Set<String>> getSupportedLanguages() {
        Set<String> languages = translationService.getSupportedLanguages();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/domains")
    public ResponseEntity<Set<String>> getSupportedDomains() {
        Set<String> domains = translationService.getSupportedDomains();
        return ResponseEntity.ok(domains);
    }

    @PostMapping("/validated-translate")
    public ResponseEntity<String> translateContent(@RequestBody TranslationRequest request) {
        try {
            translationService.validateRequest(request);
            String translatedContent = translationService.translateContent(request);
            return ResponseEntity.ok(translatedContent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
