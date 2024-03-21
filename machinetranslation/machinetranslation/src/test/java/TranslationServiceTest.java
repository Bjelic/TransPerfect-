import model.TranslationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import service.TranslationServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TranslationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TranslationServiceImpl translationService;

    @Test
    public void testTranslate() {

        when(restTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenReturn("Translated content");

        TranslationRequest request = new TranslationRequest();
        request.setSourceLanguage("en");
        request.setTargetLanguage("fr");
        request.setDomain("general");
        request.setContent("Hello");

        String result = translationService.translate(request);

        assertEquals("Translated content", result);
    }
}
