package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.board.entities.Board;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.global.exceptions.ExceptionProcessor;
import org.choongang.global.exceptions.script.AlertRedirectException;
import org.choongang.member.MemberInfo;
import org.choongang.member.MemberUtil;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validators.JoinValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member2")
@SessionAttributes("requestLogin")
public class MemberController2 implements ExceptionProcessor {

    private final JoinValidator joinValidator;
    private final MemberSaveService memberSaveService;

    private final MemberUtil memberUtil;
    private final BoardRepository repository;
    private final BoardRepository boardRepository;

    @ModelAttribute
    public RequestLogin requestLogin() {
        return new RequestLogin();
    }

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {

        boolean result = false;
        if(!result) {
            //throw new CommonException("테스트 예외", HttpStatus.BAD_REQUEST);
            //throw new AlertException("alert exception 발생", HttpStatus.BAD_REQUEST);
            //throw new AlertBackException("AlertBackException 발생,self", HttpStatus.BAD_REQUEST);
            throw new AlertRedirectException("AlertRedirectException 발생,self", "/member/login", HttpStatus.BAD_REQUEST);
        }

        return "front/member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors) {
        joinValidator.validate(form, errors);

        if (errors.hasErrors()) {
            return "front/member/join";
        }

        memberSaveService.save(form); //회원 가입 처리

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@Valid @ModelAttribute RequestLogin form, Errors errors) {
        String code = form.getCode();
        if (StringUtils.hasText(code)) {
            errors.reject(code, form.getDefaultMessage());

            // 비번 만료인 경우 비번 재설정 페이지 이동
            if (code.equals("CredentialsExpired.Login")) {
                return "redirect:/member/password/reset";
            }
        }
        return "front/member/login";
    }

    @ResponseBody
    @GetMapping("/test")
    public void test(Principal principal) {
        log.info("로그인아이디 : {}", principal.getName());
    }
/*
    @ResponseBody
    @GetMapping("/test2")
    public void test2(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info("로그인아이디 : {}", memberInfo.toString());
    }
*/
    @ResponseBody
    @GetMapping("/test3")
    public void test3() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("로그인상태 : authentication.isAuthenticated() {}", authentication.isAuthenticated());

        if(authentication.isAuthenticated() && authentication.getPrincipal() instanceof MemberInfo) { //로그인상태

            MemberInfo memberInfo = (MemberInfo) authentication.getPrincipal();
            log.info("로그인 회원 : authentication.getPrincipal() {}", authentication.getPrincipal());

        }else { //미로그인 상태
            log.info("getPrincipal() : {}", authentication.getPrincipal());
        }
    }
    @ResponseBody
    @GetMapping("/test4")
    public void test4() {
        log.info("로그인 여부 : {}", memberUtil.isLogin());
        log.info("로그인 회원 : {}", memberUtil.getMember());
    }

    @ResponseBody
    @GetMapping("/test5")
    public void test5() {

        Board board = Board.builder()
                .bId("freetalk3")
                .bName("자유게시판3")
                .build();

         boardRepository.saveAndFlush(board);
        /*
        Board board = boardRepository.findById("freetalk").orElse(null);
        board.setBName("자유게시판---2");
        boardRepository.saveAndFlush(board);
         */

        log.info("로그인 여부 : {}", memberUtil.isLogin());
        log.info("로그인 회원 : {}", memberUtil.getMember());
    }

}
