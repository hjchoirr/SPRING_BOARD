package org.choongang.global.configs;

import org.choongang.member.services.LoginFailureHandler;
import org.choongang.member.services.LoginSuccessHandler;
import org.choongang.member.services.MemberAuthenticationEntryPoint;
import org.choongang.member.services.MemberInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MemberInfoService memberInfoService) throws Exception {

        //로그인 로그아웃
        http.formLogin(f -> {
            f.loginPage("/member/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(new LoginSuccessHandler())
            .failureHandler(new LoginFailureHandler());

        });

        http.logout(f -> {
           f.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
           .logoutSuccessUrl("/member/login");
        });

        // 인가 .. 권한 설정
        http.authorizeHttpRequests( c-> {
            /*
            c.requestMatchers("/member/**").anonymous()
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated();
            */
            c.requestMatchers("/mypage/**").authenticated() //회원전용
            .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
            .anyRequest().permitAll();
        });

        //인가 실패시 유입될 포인트 지정
        http.exceptionHandling(c-> {
           c.authenticationEntryPoint(new MemberAuthenticationEntryPoint())
           .accessDeniedHandler((req, res,e) -> {
                res.sendError(HttpStatus.UNAUTHORIZED.value());
           });
        });
        //자동 로그인 설정
        http.rememberMe(c -> {
            c.rememberMeParameter("autoLogin")
                    .tokenValiditySeconds(60 * 60 * 24 * 30)
                    .userDetailsService(memberInfoService)
                    .authenticationSuccessHandler(new LoginSuccessHandler());
        });

        //iframe 출처를 같은 서버로 제한함 : 보안 관련
        http.headers( c-> c.frameOptions(f -> f.sameOrigin()));

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
