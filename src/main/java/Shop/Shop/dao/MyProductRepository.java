package Shop.Shop.dao;

import Shop.Shop.model.Category;
import Shop.Shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyProductRepository extends JpaRepository<Product, Long> {
//   Page<Product> findByCategory(Category category, Pageable pageable);
//   Page<Product> findByCategoryName(String name, Pageable pageable);
//   Page<Product> findAllByCategoryId(Long categoryID, Pageable pageable);
@Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

}
