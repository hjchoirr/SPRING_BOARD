package org.choongang.board.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestBoard {
    private Long seq; //글번호 글수정시 필요
    private String mode = "write"; // write 작성, update 수정

    @NotBlank
    private String bId;

    private String gid = UUID.randomUUID().toString();

    private boolean notice;

    @NotBlank
    private  String poster;

    private String guestPw; //비회원 글쓸때

    @NotBlank
    private  String subject;
    @NotBlank
    private  String content;

    private Long num1;
    private String text1;
    private String longText1;

}
