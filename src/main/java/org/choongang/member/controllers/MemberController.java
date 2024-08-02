package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.exceptions.ExceptionProcessor;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validators.JoinValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@SessionAttributes("requestLogin")
public class MemberController  implements ExceptionProcessor {

    private final JoinValidator joinValidator;
    private final MemberSaveService memberSaveService;
    /*
    private final MemberUtil memberUtil;
    private final BoardRepository repository;
    private final BoardRepository boardRepository;
     */
    @ModelAttribute
    public RequestLogin requestLogin() {
        return new RequestLogin();
    }

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {
        /*
        boolean result = false;
        if(!result) {
            //throw new CommonException("테스트 예외", HttpStatus.BAD_REQUEST);
            //throw new AlertException("alert exception 발생", HttpStatus.BAD_REQUEST);
            //throw new AlertBackException("AlertBackException 발생,self", HttpStatus.BAD_REQUEST);
            throw new AlertRedirectException("AlertRedirectException 발생,self", "/member/login", HttpStatus.BAD_REQUEST);
        }
        */
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

    @GetMapping("/test1")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public void test1() {
        log.info("test1 - 회원만 접근가능");
    }

    @GetMapping("/test2")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void test2() {
        log.info("test2 - 관리자만 접근가능");
    }

}
