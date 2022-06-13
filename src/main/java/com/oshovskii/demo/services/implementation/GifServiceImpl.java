package com.oshovskii.demo.services.implementation;

import com.oshovskii.demo.clients.GifClient;
import com.oshovskii.demo.services.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {
    private final GifClient gifClient;

    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.zero}")
    private String whatTag;
    @Value("${giphy.error}")
    private String errorTag;
    @Value("${giphy.api.key}")
    private String apiKey;

    @Override
    public ResponseEntity<Map> findRandomGifByTag(int gifType) {
        String gifTag;
        switch (gifType) {
            case 1:
                gifTag = richTag;
                break;
            case -1:
                gifTag = brokeTag;
                break;
            case 0:
                gifTag = whatTag;
                break;
            default:
                gifTag = errorTag;
                break;
        }

        return gifClient.getRandomGif(apiKey, gifTag);
    }
}
