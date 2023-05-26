package com.optimal.solutions.apicalls;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {
    public static String getMeaning(String word) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://api.dictionaryapi.dev/api/v2/entries/en/"+word;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody().toString();
    }

}
