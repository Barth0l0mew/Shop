package Shop.Shop.controller;

import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.Role;
import Shop.Shop.model.User;
import Shop.Shop.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private MyUserService myUserService;
    @GetMapping({"","/","index"})
    public String indexAdmin(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("UserDEtails ===== "+userDetails.getUsername());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getAuthorities().toString());
        model.addAttribute("page", "admin");
        return "admin/index.html";
    }

    @GetMapping("/userslist")
    public String showUserList(Model model) {
        model.addAttribute("page", "userlist");
        model.addAttribute("users", myUserService.findAll());
        return "admin/index";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam(name = "id") Long id, Model model) {
        System.out.println(id);
        System.out.println(Arrays.toString( Role.values()));
        User user = myUserService.findById(id);
        System.out.println(user.toString()+" ====  "+user.getRole().name());
        model.addAttribute("editUser", user);
        model.addAttribute("role", Role.values());
        model.addAttribute("page", "useredit");
        return "admin/index"; // имя вашего HTML-шаблона
    }

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        System.out.println("save user====   "+user.toString());

        myUserService.saveUser(user);
        return "redirect:/admin/userslist";
    }
    @GetMapping("/addUser")
    public String showRegistrationForm(Model model) {
        model.addAttribute("page", "adduser");
        model.addAttribute("newUser", new UserDTO()); // Создание нового объекта пользователя
        return "admin/index"; // Имя HTML файла с формой
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("newUser") UserDTO user, Model model) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getMatchingPassword());
        //myUserService.save(user);
        try {
            // Используем сервис для сохранения пользователя
            myUserService.save(user);
            return "redirect:/admin/userslist"; // Перенаправление после успешной регистрации
        } catch (IllegalArgumentException e) {
            System.out.println("eror "+e.getMessage());
            model.addAttribute("page", "adduser");
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/index"; // Возвращаем на страницу регистрации с сообщением об ошибке
        }

    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(name = "id") Long id, Model model) {
        System.out.println("delete user "+id+ " "+myUserService.findById(id));
        myUserService.deleteUser(myUserService.findById(id));
        return "redirect:/admin/userslist";
    }




}
