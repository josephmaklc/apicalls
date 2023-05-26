package com.optimal.solutions.apicalls;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "dictionaryService", url = "https://api.dictionaryapi.dev")
    public interface MyFeignClientForAPI {
        @GetMapping(value = "/api/v2/entries/en/{word}")
        String lookupWord(@PathVariable("word") String word);
}

