package Shop.Shop.service;

import Shop.Shop.dao.MyProductRepository;
import Shop.Shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyProductService {
    @Autowired
    private MyProductRepository myProductRepository;
    public List<Product> getMyProducts() {
        return myProductRepository.findAll();
    }
}
