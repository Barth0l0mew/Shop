package Shop.Shop.service;

import Shop.Shop.dao.MyCategoryRepository;
import Shop.Shop.dto.CategoryDTO;
import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.Category;
import Shop.Shop.model.Role;
import Shop.Shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MyCategoryService {
    @Autowired
    private MyCategoryRepository myCategoryRepository;
    public List<Category> findAll (){
        return myCategoryRepository.findAll();
    }
    public Category findById(Long id) {
        return myCategoryRepository.findById(id).orElse(null);
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
    public void deleteCategory(Category category) {
        myCategoryRepository.delete(category);
    }
}
