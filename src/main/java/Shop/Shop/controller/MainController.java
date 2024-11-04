package Shop.Shop.controller;

import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.*;
import Shop.Shop.service.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final MyUserService myUserService;
    @Autowired
    private final MyCategoryService myCategoryService;
    @Autowired
    private final MyProductService myProductService;
    @Autowired
    private final MyOrderService myOrderService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({"","/","index"})
       public String index(@RequestParam(defaultValue = "0") int page, Model model, @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails!=null) {
            System.out.println("UserDEtails ===== "+userDetails.getUsername());
            model.addAttribute("CartLink", true);
            System.out.println("cartlink "+true);
        } else
        {
            model.addAttribute("CartLink", false);
        }

      //  System.out.println((UserDetails)authentication.getPrincipal());
        model.addAttribute("categories", myCategoryService.findAll());
        Page<Product> productPage = myProductService.findPaginated(page, 8);
        //model.addAttribute("products", myProductService.getMyProducts());
        model.addAttribute("products", productPage);


        return "layout";
    }
    @GetMapping("/product/productlist")
    public String listProducts(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("page","productlist");
        Page<Product> productPage = myProductService.findPaginated(page, 8); // 10 продуктов на странице
        model.addAttribute("productlist", productPage);
        return "admin/index"; // возвращает имя шаблона
    }
    @GetMapping("/filter")
    public String productFilter(@RequestParam(name= "filter") Long filter, @RequestParam(defaultValue = "0", name="page") int page,  Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails!=null) {
            System.out.println("UserDEtails ===== "+userDetails.getUsername());
            model.addAttribute("CartLink", true);
            System.out.println("cartlink "+true);
        } else
        {
            model.addAttribute("CartLink", false);
        }
        System.out.println("filter "+filter+ " page "+page);
        model.addAttribute("categories", myCategoryService.findAll());
        //myProductService.findByCategory(filter)
        //Page<Product> productPage = myProductService.wrapListInPage(myProductService.findByCategory(filter), page, 2);
        // model.addAttribute("products", myProductService.findByCategory(filter));
       // Page<Product> products =  new PageImpl<Product>(productPage.getContent(), PageRequest.of(page, 2), productPage.getTotalElements());
        Category category = myCategoryService.findById(filter);
        Page<Product> productPage = myProductService.findPaginatedByCategory(filter, page, 8);

        System.out.println(productPage.getContent());
        System.out.println("total "+productPage.getTotalElements());
        System.out.println("Totale page "+productPage.getTotalPages());
        System.out.println("NUmber "+productPage.getNumber());
        model.addAttribute("filter", filter);
        model.addAttribute("products", productPage);
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
    @GetMapping("/product")
    public String showProduct(@RequestParam(name = "id") Long id, Model model,  @AuthenticationPrincipal UserDetails userDetails) {
      //  Long id = 37L;
        if (userDetails!=null) {
            System.out.println("UserDEtails ===== "+userDetails.getUsername());
            model.addAttribute("CartLink", true);
            System.out.println("cartlink "+true);
        } else
        {
            model.addAttribute("CartLink", false);
        }
        model.addAttribute("product",myProductService.findById(id));
        return "product";
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
    @PostMapping ("/buy/product")
    public String buy(@RequestParam(name = "id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        //, @PathVariable Long productId
        User user = myUserService.findByUser(userDetails.getUsername());
        Product product = myProductService.findById(id);
        myUserService.addProductToBasket(user,product);
        return "redirect:/index";
    }
    @GetMapping("/basket")
    public String showBasket(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = myUserService.findByUser(userDetails.getUsername());
        System.out.println("products "+user.getProducts());
        System.out.println(user.getProducts()==null);
        System.out.println(user.getProducts().isEmpty());
        model.addAttribute("CartLink", true);
        model.addAttribute("products", user.getProducts());

        return "basket";
    }
    @PostMapping("/deleteproduct")
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "id") Long id, Model model) {
        System.out.println("delete product "+id+ " "+myProductService.findById(id));
        User user = myUserService.findByUser(userDetails.getUsername());
        Product product = myProductService.findById(id);
        myUserService.removeProductFromBasket(user, product);
       // myProductService.deleteProduct(myProductService.findById(id));
        return "redirect:/basket";
    }
    @PostMapping ("/order")
    public String order(@AuthenticationPrincipal UserDetails userDetails, Model model) throws MessagingException {
        User user = myUserService.findByUser(userDetails.getUsername());

       try {
           Email email = new Email();
           email.setTo(user.getEmail());
           email.setFrom("barth0l0mew@mail.ru");
           email.setSubject("Онлайн заказ");
           email.setTemplate("email.html");
           Map<String, Object> properties = new HashMap<>();
           properties.put("name", user.getUsername());
           properties.put("subscriptionDate", LocalDate.now().toString());
           properties.put("products", user.getProducts());
           properties.put("totatPrice",user.getProducts().stream()
                   .map(Product::getPrice)
                   .reduce(BigDecimal.ZERO, BigDecimal::add));
           email.setProperties(properties);
           emailService.sendHtmlMessage(email);

       } catch (Exception e) {
           System.out.println("Неверный email address");
           model.addAttribute("errorMessage", "Неверный email address");
           model.addAttribute("CartLink", true);
           model.addAttribute("products", user.getProducts());
           return "basket";
       }
        System.out.println("order user "+user.toString());
        myOrderService.save(user);
        return "redirect:/index";
    }
    @GetMapping ("/newadmin")
    public String newAdmin(Model model) {
        UserDTO user = UserDTO.builder()
                .username("superadmin")
                .email("admin@mail.ru")
                .password("admin")
                .matchingPassword("admin")
                .build();
        System.out.println("new user admnin "+user.toString());
        myUserService.newAdmin(user);
        return  "redirect:/index";
    }
}
