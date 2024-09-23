package Shop.Shop.controller;

import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.ProductDTO;
import Shop.Shop.service.MyCategoryService;
import Shop.Shop.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private MyProductService myProductService;
    @Autowired
    private MyCategoryService myCategoryService;
    @GetMapping ("/product/productlist")
    public String productList(Model model){
        model.addAttribute("page","productlist");
        model.addAttribute("productlist",myProductService.getMyProducts());
        return "admin/index";
    }
    @GetMapping("/product/addproduct")
    public String showRegistrationForm(Model model) {
        model.addAttribute("page", "addproduct");
        model.addAttribute("newProduct", new ProductDTO());
        model.addAttribute("categories", myCategoryService.findAll());
        return "admin/index"; // Имя HTML файла с формой
    }
}
