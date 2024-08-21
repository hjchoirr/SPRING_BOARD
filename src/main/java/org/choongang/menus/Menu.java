package org.choongang.menus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private final static Map<String, List<MenuDetail>> menus;

    static {
        menus = new HashMap<>();
        menus.put("board", Arrays.asList(
                new MenuDetail("list", "/board", "게시판 목록"),
                new MenuDetail("add", "/board/add", "게시판 등록"),
                new MenuDetail("posts", "/board/posts", "게시물 관리")
        ));
    }

    public static List<MenuDetail> getSubMenus(String code) {
        return menus.get(code);
    }
}
