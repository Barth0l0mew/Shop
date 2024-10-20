package Shop.Shop.service;

import Shop.Shop.dao.MyCategoryRepository;
import Shop.Shop.dao.MyProductRepository;
import Shop.Shop.dao.MyUserRepository;
import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.ProductDTO;
import Shop.Shop.model.Category;
import Shop.Shop.model.Product;
import Shop.Shop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyProductService {
    @Autowired
    private MyProductRepository myProductRepository;
    @Autowired
    private MyCategoryRepository myCategoryRepository;
    @Autowired
    private MyUserRepository myUserRepository;

    public List<Product> getMyProducts() {
        return myProductRepository.findAll();
    }
    public Product findById(Long id) {
        return myProductRepository.findById(id).orElse(null);
    }

    public Page<Product> findPaginated(int page, int size) {
        return myProductRepository.findAll(PageRequest.of(page, size));
    }
    public List<Product> findByCategory(Long filter) {
        List<Product> products = new ArrayList<>();
        for (Product product : myProductRepository.findAll()) {
            for (Category category : product.getCategories()) {
                if (category.getId()==filter)
                    products.add(product);
            }
        }
        return products;
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
        List<User> users = myUserRepository.findAll();
        for (User user : users) {
            user.getProducts().remove(product);
        }
        myProductRepository.delete(product);
    }
    public void saveProduct(Product Product) {
        myProductRepository.save(Product);

    }
}
