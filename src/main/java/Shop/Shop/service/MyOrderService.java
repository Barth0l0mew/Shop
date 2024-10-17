package Shop.Shop.service;

import Shop.Shop.dao.MyCategoryRepository;
import Shop.Shop.dao.MyOrderRepository;
import Shop.Shop.dao.MyProductRepository;
import Shop.Shop.dao.MyUserRepository;
import Shop.Shop.dto.ProductDTO;
import Shop.Shop.model.Order;
import Shop.Shop.model.Product;
import Shop.Shop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyOrderService {
    @Autowired
    private MyOrderRepository myOrderRepository;
    @Autowired
    private MyProductRepository myProductRepository;
    @Autowired
    private MyCategoryRepository myCategoryRepository;
    @Autowired
    private MyUserRepository myUserRepository;

    public List<Order> getMyOrders() {
        return myOrderRepository.findAll();
    }
    public Order findById(Long id) {
        return myOrderRepository.findById(id).orElse(null);
    }

    public Order save(User user) {
        Order order = Order.builder()
                .created(LocalDateTime.now())
                .update(LocalDateTime.now())
                .address(user.getEmail())
                .products(
                        user.getProducts().stream()
                                .map(Product::getTitle)
                                .collect(Collectors.joining(", "))
                )


                .build();

        return  myOrderRepository.save(order);
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
