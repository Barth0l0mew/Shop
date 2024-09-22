package Shop.Shop.dao;

import Shop.Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyProductRepository extends JpaRepository<Product, Long> {

}
