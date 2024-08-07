package org.choongang.RestaurantTest;

import org.choongang.global.rests.gov.restaurant.ResResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
@ActiveProfiles("test")
public class RestTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void test1() {
        String url = "https://seoul.openapi.redtable.global/api/rstr?serviceKey=8Mu7gNxO98975QV25VMKBnsDC82WaomG1raYEiOXoi3kOTGsi89KCUJBxZI0HNz6&pageNo=2";

        //ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseEntity<ResResponse> response = restTemplate.getForEntity(URI.create(url), ResResponse.class);

        System.out.println( response);
    }

}
