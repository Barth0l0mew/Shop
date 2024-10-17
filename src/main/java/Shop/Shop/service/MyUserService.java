package Shop.Shop.service;

import Shop.Shop.dao.MyBasketRepository;
import Shop.Shop.dao.MyUserRepository;
import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.Basket;
import Shop.Shop.model.Product;
import Shop.Shop.model.Role;
import Shop.Shop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyBasketRepository myBasketRepository;

    public List<User> findAll() {
        return myUserRepository.findAll();
    }
    public User findById(Long id) {
        return myUserRepository.findById(id).orElse(null);
    }
    public void saveUser(User user) {
        User userOld = myUserRepository.findById(user.getId()).orElse(null);
        if (!userOld.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        myUserRepository.save(user);
    }
    public User findByUser(String username) {
        Optional<User> user = myUserRepository.findByUsername(username);
        return user.orElse(null);
    }
   public User findByUsernameAndPassword(String username, String password) {
       System.out.println("==find by username "+username);
        Optional<User> user = myUserRepository.findByUsername(username);

       if (!user.isPresent() || !user.get().getPassword().equals(password)) {
           throw new IllegalArgumentException("Неправильный логин или пароль");
       } else
        return user.orElse(null);
   }
   public void deleteUser(User user) {
        myUserRepository.delete(user);
   }

   public User save(UserDTO userDTO) {
       System.out.println(userDTO.toString());
       Optional<User> existingUser = myUserRepository.findByEmail(userDTO.getEmail());
       if (existingUser.isPresent()) {
           throw new IllegalArgumentException("Пользователь с таким email уже существует");
       }
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }else {
            // Basket basket =  Basket.builder().build();
           // Basket basket = myBasketRepository.save(Basket.builder().build());
          //  Basket basket = Basket.builder().build();
           // System.out.println("new basket "+basket.toString());
            //myBasketRepository.save(basket);
            //Basket basketNew = myBasketRepository.findById(Long.valueOf(myBasketRepository.findAll().size())).orElse(null);
            //System.out.println();
            Basket basket =  Basket.builder().title("title1").build();
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .archive(false)
                    .email(userDTO.getEmail())
                    .role(Role.USER)
                  //  .basket(basket)
                    .products(null)
                    .build();
          //  user.getBasket().setUser(user);
           // basket.setUser(user);

            //user.setBasket(basket);
            //myBasketRepository.save(basket);
            /*System.out.println("=== "+myBasketRepository.save(basket));
            user.setBasket(basket);
            */
            //System.out.println("=== "+myBasketRepository.save(basket));
           // basket.setUser(user);
           // myBasketRepository.save(basket);
           User user1 = myUserRepository.save(user);
            System.out.println("user1 "+user1.toString());
            //user1.getBasket().setUser(user);
            //System.out.println("user 1 "+ myUserRepository.save(user1));
            //  myBasketRepository.save(basket);

          //  myBasketRepository.save(basket);
          // return myUserRepository.save(user);
            return user;
            }
    }
    public void addProductToBasket(User user, Product product) {
        //Basket basket = user.getBasket();
        System.out.println("user "+user.toString());
        System.out.println("product "+product.toString());
        user.getProducts().add(product);
        System.out.println("user and product "+user.toString());
        myUserRepository.save(user);
       // Basket basket = myBasketRepository.findById(user.getBasket().getId()).orElse(null);
        //basket.getProducts().add(product);
      //  myBasketRepository.save(basket);
        //List<Product> products = basket.getProducts();
       // products.add(product);
       // basket.setProducts(products);

       // myBasketRepository.save(basket);
    }
    public void removeProductFromBasket(User user, Product product) {
        System.out.println("user "+user.toString());
        System.out.println("product "+product.toString());
        user.getProducts().remove(product);
        myUserRepository.save(user);
    }

}
