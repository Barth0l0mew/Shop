package Shop.Shop.controller;

import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.Category;
import Shop.Shop.model.Product;
import Shop.Shop.model.User;
import Shop.Shop.service.MyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    public MyCategoryService myCategoryService;

//    @GetMapping ("/categorylist")
//    public String categoryList(Model model){
//        model.addAttribute("page","categorylist");
//        System.out.println("====controller ==="+myCategoryService.findAll());
//        model.addAttribute("categories", myCategoryService.findAll() );
//        return "admin/index";
//    }
@GetMapping("/categorylist")
public String listCategory(@RequestParam(defaultValue = "0") int page, Model model) {
    model.addAttribute("page","categorylist");
    Page<Category> productPage = myCategoryService.findPaginated(page, 10); // 10 категорий на странице
    model.addAttribute("categories", productPage);
    return "admin/index"; // возвращает имя шаблона
}
    @GetMapping("/addCategory")
    public String showRegistrationForm(Model model) {
        model.addAttribute("page", "addcategory");
        model.addAttribute("newCategory", new CategoryDTO());
        return "admin/index"; // Имя HTML файла с формой
    }
    @PostMapping("/category/registration")
    public String registration(@ModelAttribute("newCategory") CategoryDTO category, Model model) {
        System.out.println("+++++new category "+category.getTitle());
        try {
            // Используем сервис для сохранения пользователя
            myCategoryService.save(category);
            return "redirect:/admin/categorylist"; // Перенаправление после успешной регистрации
        } catch (IllegalArgumentException e) {
            System.out.println("eror "+e.getMessage());
            model.addAttribute("page", "addcategory");
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/index"; // Возвращаем на страницу регистрации с сообщением об ошибке
        }
    }
    @PostMapping("/category/deletecategory")
    public String deleteUser(@RequestParam(name = "id") Long id, Model model) {
        System.out.println("delete category "+id+ " "+myCategoryService.findById(id));
        myCategoryService.deleteCategory(myCategoryService.findById(id));
        return "redirect:/admin/categorylist";
    }
    @PostMapping("/category/categoryedit")
    public String editUser(@RequestParam(name = "id") Long id, Model model) {
        System.out.println(id);
        Category category = myCategoryService.findById(id);
        System.out.println(category.toString());
        model.addAttribute("categoryedit", category);
        model.addAttribute("page", "categoryedit");
        return "admin/index"; // имя вашего HTML-шаблона
    }
    @PostMapping("/category/saveCategory")
    public String saveCategory(Category category) {
        System.out.println("save category "+category);
        myCategoryService.saveCategory(category);
        return "redirect:/admin/categorylist";
    }
}
