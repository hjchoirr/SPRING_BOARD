package org.choongang.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private ObjectMapper om;

    @Test
    void test1() throws JsonProcessingException {

        String url = "https://seoul.openapi.redtable.global/api/rstr?serviceKey=8Mu7gNxO98975QV25VMKBnsDC82WaomG1raYEiOXoi3kOTGsi89KCUJBxZI0HNz6&pageNo=2";

        ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
        System.out.println(response);
        //System.out.println(response.getStatusCode());

        //ResResponse resResponse = om.readValue(response.getBody(), ResResponse.class);
        //System.out.println(resResponse);

    }

}
