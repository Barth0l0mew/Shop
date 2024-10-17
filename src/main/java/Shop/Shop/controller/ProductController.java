package Shop.Shop.controller;

import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.ProductDTO;
import Shop.Shop.model.Category;
import Shop.Shop.model.Product;
import Shop.Shop.service.MyCategoryService;
import Shop.Shop.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public String addProduct(@RequestParam("file") MultipartFile file, @ModelAttribute("newProduct") ProductDTO productDTO) {
        System.out.println("+++newproductDTo++"+ productDTO.toString());
        String fileName = file.getOriginalFilename();
        System.out.println("fileName==========="+fileName);
        if (!fileName.equals("")){

            File uploadedDirectory = new File("src/main/resources/static/uploaded/");
            System.out.println(uploadedDirectory);
            try {
                    file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                    productDTO.setLink("/uploaded/" + fileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            productDTO.setLink("/image/not-found.png");
        }

        myProductService.save(productDTO);
        return "redirect:/admin/product/productlist";
    }
    @PostMapping("/product/deleteproduct")
    public String deleteUser(@RequestParam(name = "id") Long id, Model model) {
        System.out.println("delete category "+id+ " "+myProductService.findById(id));
        myProductService.deleteProduct(myProductService.findById(id));
        return "redirect:/admin/product/productlist";
    }
    @PostMapping("/product/productedit")
    public String editproduct(@RequestParam(name = "id") Long id, Model model) {
        System.out.println(id);
        Product product = myProductService.findById(id);
        System.out.println("======productEdit======= "+product.toString());
        model.addAttribute("categories", myCategoryService.findAll());
        model.addAttribute("editProduct", product);
        model.addAttribute("page", "productedit");
        return "admin/index"; // имя вашего HTML-шаблона
    }
    @PostMapping("/product/saveProduct")
    public String saveCategory( @RequestParam(name = "id") Long id, @RequestParam("file") MultipartFile file, @ModelAttribute Product product) {
        System.out.println("save product "+id+" "+product.toString());
        String fileName = file.getOriginalFilename();
        System.out.println("fileName==========="+fileName+"    product image"+product.getLink());

        if (!fileName.equals("")){

            File uploadedDirectory = new File("src/main/resources/static/uploaded/");
            System.out.println(uploadedDirectory);
            try {
                file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                product.setLink("/uploaded/" + fileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
        {
            product.setLink(myProductService.findById(product.getId()).getLink());
        }
        if (product.getLink()== null)
            product.setLink("/image/not-found.png");
        System.out.println("download product "+myProductService.findById(product.getId()).toString());
        System.out.println("=====Final save produc "+product.toString());
        myProductService.saveProduct(product);
        return "redirect:/admin/product/productlist";
    }
}
