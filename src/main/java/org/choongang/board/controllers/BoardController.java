package org.choongang.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Board;
import org.choongang.board.services.BoardConfigService;
import org.choongang.global.Utils;
import org.choongang.menus.Menu;
import org.choongang.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final Utils utils;
    private final BoardConfigService boardConfigService;

    @ModelAttribute("menuCode")
    public String getMenuCode() {
        return "board";
    }

    @ModelAttribute("subMenus")
    public List<MenuDetail> getMenuDetail() {
        return Menu.getSubMenus("board");
    }

    @GetMapping
    public String list(@ModelAttribute BoardSearch form, Model model) {
        List<Board> boards = boardConfigService.getBoards();

        model.addAttribute("boards", boards);
        return utils.tpl("board/list");
    }

    @GetMapping("/add")
    public String add(RequestBoardConfig form) {

        return utils.tpl("board/add");
    }
    @GetMapping("/edit/{bId}")
    public String edit(@PathVariable("bId") String bId,  RequestBoardConfig form) {

        Board board = boardConfigService.getBoard(bId);
        form.setBId(board.getBId());
        form.setBName(board.getBName());
        form.setActive(board.getActive());
        return utils.tpl("board/edit");
    }
    @PostMapping("/save")
    public String save(RequestBoardConfig form) {
        System.out.println(form);

        boardConfigService.save(form);
        return utils.tpl("board/add");
    }
}
