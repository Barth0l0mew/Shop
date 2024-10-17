package Shop.Shop.dao;

import Shop.Shop.model.Basket;
import Shop.Shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderRepository extends JpaRepository<Order, Long> {

}
