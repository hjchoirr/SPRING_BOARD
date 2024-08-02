package org.choongang.email;

import org.choongang.email.entities.EmailMessage;
import org.choongang.email.service.EmailSendService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Ex01 {
    @Autowired
    private EmailSendService mailSendService;

    @Test
    void test1() throws Exception {
        EmailMessage emailMessage = new EmailMessage("hjchoir@naver.com", "구글에서 스프링으로 보냄", "메일 내요...");
        boolean result = mailSendService.sendEmail(emailMessage);
        System.out.println(result);
    }

    @Test
    @DisplayName("템플릿 메일 테스트")
    void test2() throws Exception {
        EmailMessage message = new EmailMessage("hjchoir@naver.com", "구글에서 스프링으로 보냄: 템플릿", "메일 내용...");
        Map<String, Object> tplData = new HashMap<>();
        tplData.put("authNum", "1234");
        boolean result = mailSendService.sendEmail(message, "auth", tplData);
        System.out.println(result);
    }
}
