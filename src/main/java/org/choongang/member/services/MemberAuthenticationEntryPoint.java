package org.choongang.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        System.out.println(authException);

        //화원전용 페이지 접근하면 : /mypage -> 로그인페이지로 이동
        //관리자 페이지로 접근한 경우 - 응답코드 401, 에러페이지 출력

        String uri = request.getRequestURI();
        if(uri.contains("/admin")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            String qs = request.getQueryString();
            String redirectUrl = uri.replace(request.getContextPath(), "");
            if(StringUtils.hasText(qs)) {
                redirectUrl += "?" + qs;
            }
            response.sendRedirect(request.getContextPath() + "/member/login?redirectUrl=" +  redirectUrl);
        }
    }
}
