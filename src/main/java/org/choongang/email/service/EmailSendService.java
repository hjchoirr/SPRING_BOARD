package org.choongang.email.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.choongang.email.entities.EmailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public boolean sendEmail(EmailMessage message, String tpl, Map<String, Object> tplData) {
        String text = null;

        if(StringUtils.hasText(tpl)) {
            tplData = Objects.requireNonNullElse(tplData, new HashMap<>());
            Context context = new Context();

            tplData.put("to", message.to());
            tplData.put("subject", message.subject());
            tplData.put("message", message.message());

            context.setVariables(tplData);
            text = templateEngine.process("email/" + tpl, context);
        } else {
            text = message.message();
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(message.to());
            mimeMessageHelper.setSubject(message.subject());
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendEmail(EmailMessage message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setTo(message.to());
            mimeMessageHelper.setSubject(message.subject());
            mimeMessageHelper.setText(message.message(), true);
            mailSender.send(mimeMessage);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
