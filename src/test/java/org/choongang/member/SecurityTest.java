package org.choongang.member;

import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
//@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberRepository repository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void test1() throws Exception {
        mockMvc.perform(post("/member/join")
                        .with(csrf().asHeader())  // 헤더에 토큰 실어 보내기
                        .param("email", "user02@test.com"))
                .andDo(print());
    }
    @Test
    @WithMockUser // 로그인된 상태로 테스트
    void test2() throws Exception{
        mockMvc.perform(get("/mypage"))
                .andDo(print());
    }
    @Test
    @WithMockUser(username="user02@test.com", authorities = "ADMIN") // 로그인된 상태로 테스트
    void test3() throws Exception{
        mockMvc.perform(get("/admim"))
                .andDo(print());
    }
    @Test
    void test4() {
        Member member = memberRepository.findByEmail("user02@test.com").orElse(null);
        System.out.println(member);

    }
}
