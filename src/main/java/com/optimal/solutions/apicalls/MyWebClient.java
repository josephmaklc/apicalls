package com.optimal.solutions.apicalls;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MyWebClient {

    private final WebClient client;

    public MyWebClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("https://api.dictionaryapi.dev/").build();
    }

    public String lookupWord(String word) {
        Mono<String> result = this.client.get().uri("api/v2/entries/en/"+word).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);


        return result.block();
    }

}