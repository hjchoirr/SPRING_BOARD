package org.choongang.drug;

import org.choongang.global.rests.gov.drug.LocalData_010106_Sm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
@ActiveProfiles("test")
public class DrugAptTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void test1() {
        String url = "http://openapi.seoul.go.kr:8088/sample/json/LOCALDATA_010106_SM/1/5/";

        //ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseEntity<LocalData_010106_Sm> response = restTemplate.getForEntity(URI.create(url), LocalData_010106_Sm.class);

        System.out.println("response=" + response);
    }

}
