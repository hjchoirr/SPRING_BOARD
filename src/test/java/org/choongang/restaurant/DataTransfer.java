package org.choongang.restaurant;

import org.choongang.restraurent.services.DataTransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@ActiveProfiles("test")
public class DataTransfer {

    @Autowired
    private DataTransferService service;

    @Test
    void update1() {
        for(int i = 1 ; i <= 10; i++) {
            service.update1(i);
        }

    }
    @Test
    @DisplayName("식당이미지")
    void update2() {
        //for(int i = 1 ; i <= 10; i++) {
            service.update2(1);
        //}

    }

    @Test
    @DisplayName("메뉴기본정보")
    void update3() {
        for(int i = 1 ; i <= 10; i++) {
        service.update3(1);
        }

    }

}
