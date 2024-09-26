package Shop.Shop.controller;

import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.ProductDTO;
import Shop.Shop.service.MyCategoryService;
import Shop.Shop.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(myProductService.getMyProducts());
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
    @PostMapping("product/registration")
    public String addProduct(@ModelAttribute("newProduct") ProductDTO productDTO) {
        System.out.println("+++newproductDTo++"+ productDTO.toString());
        myProductService.save(productDTO);
        return "redirect:/admin/product/productlist";
    }
    @PostMapping("/product/deleteproduct")
    public String deleteUser(@RequestParam(name = "id") Long id, Model model) {
        System.out.println("delete category "+id+ " "+myProductService.findById(id));
        myProductService.deleteProduct(myProductService.findById(id));
        return "redirect:/admin/product/productlist";
    }
}
