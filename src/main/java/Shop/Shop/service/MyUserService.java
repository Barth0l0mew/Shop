package Shop.Shop.service;

import Shop.Shop.dao.MyUserRepository;
import Shop.Shop.dto.UserDTO;
import Shop.Shop.model.Role;
import Shop.Shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository myUserRepository;
    public List<User> findAll() {
        return myUserRepository.findAll();
    }
    public User findById(Long id) {
        return myUserRepository.findById(id).orElse(null);
    }
    public void saveUser(User user) {
        myUserRepository.save(user);
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
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .archive(false)
                    .email(userDTO.getEmail())
                    .role(Role.USER)
                    .basket(null)
                    .build();
            System.out.println(user.toString());
           return myUserRepository.save(user);
            }
    }

}
