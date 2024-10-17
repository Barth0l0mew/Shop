package Shop.Shop.dao;

import Shop.Shop.model.Basket;
import Shop.Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBasketRepository extends JpaRepository<Basket, Long> {

}
