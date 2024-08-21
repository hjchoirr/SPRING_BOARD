package org.choongang.board.services;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoardConfig;
import org.choongang.board.entities.Board;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.global.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigService {
    private final BoardRepository boardRepository;
    private final Utils utils;

    public void save(RequestBoardConfig form) {

        String bId = form.getBId();
        String bName = form.getBName();
        Board board = boardRepository.findById(bId).orElseGet(Board::new);

        board.setBId(bId);
        board.setBName(bName);

        Board board1 = boardRepository.saveAndFlush(board);

        System.out.println(board1);

    }

    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

}
