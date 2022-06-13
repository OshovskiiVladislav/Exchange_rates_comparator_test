package com.oshovskii.demo.services.implementation;

import com.oshovskii.demo.clients.GifClient;
import com.oshovskii.demo.services.GifService;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Import(GifServiceImpl.class)
@ExtendWith(SpringExtension.class)
@DisplayName("GifService test")
@TestPropertySource(properties = {
        "giphy.rich = rich",
        "giphy.broke = broke",
        "giphy.zero = zero",
        "giphy.error = error",
        "giphy.api.key = key"
})
class GifServiceImplTest {

    @SpyBean
    private GifService gifService;

    @MockBean
    private GifClient gifClientMock;

    @Test
    @DisplayName("findRandomGifByTag() " +
            "with void input " +
            "should return random gif by tag test")
    void findRandomGifByTag_voidInout_shouldReturnExpectedGif() {
        // Config
        val sourceGifType = 1;
        val sourceApiKey = "key";
        val expectedGifTag = "rich";
        val expectedResponseEntity = new ResponseEntity<Map>(HttpStatus.OK);

        when(gifClientMock.getRandomGif(sourceApiKey, expectedGifTag))
                .thenReturn(expectedResponseEntity);

        // Call
        val actualResponseEntity = gifService.findRandomGifByTag(sourceGifType);

        // Verify
        assertEquals(expectedResponseEntity, actualResponseEntity);
    }
}