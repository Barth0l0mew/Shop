package Shop.Shop.service;

import Shop.Shop.dao.MyCategoryRepository;
import Shop.Shop.dao.MyProductRepository;
import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.Category;
import Shop.Shop.model.Product;
import Shop.Shop.model.Role;
import Shop.Shop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MyCategoryService {
    @Autowired
    private MyProductRepository myProductRepository;
    @Autowired
    private MyCategoryRepository myCategoryRepository;
    public List<Category> findAll (){
        return myCategoryRepository.findAll();
    }
    public Category findById(Long id) {
        return myCategoryRepository.findById(id).orElse(null);
    }
    public Page<Category> findPaginated(int page, int size) {
        return myCategoryRepository.findAll(PageRequest.of(page, size));
    }
    public Category save(CategoryDTO categoryDTO) {
        System.out.println("mcategory service "+categoryDTO.toString());
        Optional<Category> existingCategory = myCategoryRepository.findCategoryByTitle(categoryDTO.getTitle());
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Категория с таким назвнием существует");
        }
        else {
            Category category = Category.builder()
                    .title(categoryDTO.getTitle())
                    .build();
            System.out.println(category.toString());
            return myCategoryRepository.save(category);
        }
    }
    public void saveCategory(Category category) {
        myCategoryRepository.save(category);
    }
    @Transactional
    public void deleteCategory(Category category) {
        System.out.println("delete category service "+category.toString());
        List<Product> products = myProductRepository.findAll();
        for (Product product: products){
                product.getCategories().remove(category);
        }
        //        for (Product product : category.getProducts()) {
//            System.out.println("=======delete product service "+product.toString());
//            product.getCategories().remove(category);
//        }
//        List<Product> products = myProductRepository.findByCategories_Id(categoryId);
//        for (Product product : products) {
//            product.getCategories().removeIf(category -> category.getId().equals(categoryId));
//            productRepository.save(product); // обновляем продукт
//        }
        myCategoryRepository.delete(category); //old
    }
}
