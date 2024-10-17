package Shop.Shop.dao;

import Shop.Shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyCategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByTitle(String title);
    Optional<Category> findById(Long id);


}
