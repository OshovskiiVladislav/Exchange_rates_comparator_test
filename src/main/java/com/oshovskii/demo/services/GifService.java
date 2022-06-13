package com.oshovskii.demo.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GifService {
    ResponseEntity<Map> findRandomGifByTag(int gifType);
}
