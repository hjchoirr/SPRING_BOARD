package org.choongang.board.controllers;

import lombok.Data;
import org.choongang.global.CommonSearch;

import java.util.List;

@Data
public class BoardDataSearch extends CommonSearch {
    private String bId; //게시판Id
    private List<String> bIds; // 게시판 ID 여러개

    private String sort; //정렬조건
}
