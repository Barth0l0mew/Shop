package Shop.Shop.controller;

import Shop.Shop.model.Email;
import Shop.Shop.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmailController {
    @Autowired
    private  EmailService emailService;

    @GetMapping("/send")
    public String send (Model model) throws MessagingException {
        Email email = new Email();
        email.setTo("dim140192@gmail.com");
        email.setFrom("barth0l0mew@mail.ru");
        email.setSubject("Simple text");
        email.setTemplate("email.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Ashish");
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("Python", "Go", "C#"));
        email.setProperties(properties);
        emailService.sendHtmlMessage(email);
        return "redirect:/index";
    }

}
