package Shop.Shop.controller;

import Shop.Shop.dto.UserDTO;
import Shop.Shop.service.MyCategoryService;
import Shop.Shop.service.MyProductService;
import Shop.Shop.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final MyUserService myUserService;
    @Autowired
    private final MyCategoryService myCategoryService;
    @Autowired
    private final MyProductService myProductService;
    @GetMapping({"","/","index"})

    public String index(Model model) {
        model.addAttribute("categories", myCategoryService.findAll());
        model.addAttribute("products", myProductService.getMyProducts());
        return "layout";
    }
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO()); // Создание нового объекта пользователя
        return "registration"; // Имя HTML файла с формой
    }
    @PostMapping("/register")
    public String registration(@ModelAttribute("user") UserDTO user, Model model) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getMatchingPassword());
        //myUserService.save(user);
        try {
            // Используем сервис для сохранения пользователя
            myUserService.save(user);
            return "redirect:/login"; // Перенаправление после успешной регистрации
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "registration"; // Возвращаем на страницу регистрации с сообщением об ошибке
        }

    }
//    @GetMapping ("/login")
//    public String showLoginForm(Model model) {
//       // model.addAttribute("user", new UserDTO());
//        return "login";
//    }
    @PostMapping("/log")
    public String login(@ModelAttribute("user") UserDTO user, Model model) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        try {
            // Используем сервис для сохранения пользователя
            myUserService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            return "layout"; // Перенаправление после успешной регистрации
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login"; // Возвращаем на страницу регистрации с сообщением об ошибке
        }

    }
}
