package org.choongang.board.service;

import org.choongang.board.controllers.RequestBoardConfig;
import org.choongang.board.services.BoardConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardConfigServiceTest {
    @Autowired
    private BoardConfigService boardConfigService;

    @Test
    void testSave() {
        RequestBoardConfig form = RequestBoardConfig.builder()
                .bId("freeBoard")
                .bName("자유게시판1")
                .active(true)
                .build();
        boardConfigService.save(form);

    }
}
