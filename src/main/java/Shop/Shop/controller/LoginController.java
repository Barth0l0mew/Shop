package Shop.Shop.controller;

import Shop.Shop.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
         model.addAttribute("user", new UserDTO());
        return "login";
    }
    @GetMapping("/login2")
    public String login2() {
        return "login2";
    }
}
