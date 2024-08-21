package org.choongang.board.service;

import org.choongang.board.controllers.RequestBoardData;
import org.choongang.board.entities.Board;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.board.services.BoardDataSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@ActiveProfiles("test")
public class BoardDataSaveServiceTest {
    @Autowired
    private BoardDataSaveService boardDataSaveService;

    @Autowired
    private BoardRepository boardRepository;
    Board board;

    @BeforeEach
    void init() {

         board = Board.builder()
                .bId("notice1")
                .bName("공지사항게시판1")
                .build();
        boardRepository.saveAndFlush(board);
    }

    @Test
    void saveTest() {
        RequestBoardData form = new RequestBoardData();
        form.setBId(board.getBId());
        form.setPoster("작성자2");
        form.setSubject("제목2");
        form.setContent("내용2");
        BoardData data = boardDataSaveService.save(form);

        System.out.println("data1 : " + data);

        form.setMode("update");
        form.setSeq(data.getSeq());
        form.setSubject("(수정)제목2");
        BoardData data2 = boardDataSaveService.save(form);
        System.out.println("data2 : " + data2);

    }
}
