package Shop.Shop.service;

import Shop.Shop.dao.MyCategoryRepository;
import Shop.Shop.dao.MyProductRepository;
import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.ProductDTO;
import Shop.Shop.model.Category;
import Shop.Shop.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyProductService {
    @Autowired
    private MyProductRepository myProductRepository;
    @Autowired
    private MyCategoryRepository myCategoryRepository;

    public List<Product> getMyProducts() {
        return myProductRepository.findAll();
    }
    public Product findById(Long id) {
        return myProductRepository.findById(id).orElse(null);
    }

    public Product save(ProductDTO productDTO) {
        System.out.println("mcategory service "+productDTO.toString());
            Product product = Product.builder()
                    .title(productDTO.getTitle())
                    .description(productDTO.getDescription())
                    .price(productDTO.getPrice())
                    .categories(productDTO.getCategories())
                    .link(productDTO.getLink())
                    .build();

            System.out.println(product.toString());
        return  myProductRepository.save(product);
    }
    @Transactional
    public void deleteProduct(Product product) {
        myProductRepository.delete(product);
    }
    public void saveProduct(Product Product) {
        myProductRepository.save(Product);

    }
}
