package org.choongang.tour.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiUpdateTest {
    @Autowired
    private ApiUpdateService service;
    @Test
    void test1() {
        service.update();
    }
}
