package model;

import lombok.Data;

@Data
public class TranslationRequest {

    private String sourceLanguage;
    private String targetLanguage;
    private String domain;
    private String content;


}
