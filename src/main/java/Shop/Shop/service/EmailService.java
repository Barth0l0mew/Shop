package Shop.Shop.service;

import Shop.Shop.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
//@RequiredArgsConstructor
//@Data
//@AllArgsConstructor

public class EmailService {
    //почтовый отправитель
    @Autowired
    private  JavaMailSender javaMailSender;
    @Autowired
    //обработчик шаблонов
    private  SpringTemplateEngine springTemplateEngine;

    public void sendHtmlMessage(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = springTemplateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        //log.info ("Sending email: {} with html body: {}", email, html);
        javaMailSender.send(message);
    }
}
