package Shop.Shop.controller;

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
    @GetMapping ("/product/productlist")
    public String productList(Model model){
        model.addAttribute("page","productlist");
        model.addAttribute("productlist",myProductService.getMyProducts());
        return "admin/index";
    }
}
