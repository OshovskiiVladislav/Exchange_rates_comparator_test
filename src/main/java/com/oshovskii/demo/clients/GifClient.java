package com.oshovskii.demo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "giphyClient", url = "${giphy.url.general}")
public interface GifClient {

    @GetMapping("/random")
    ResponseEntity<Map> getRandomGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
