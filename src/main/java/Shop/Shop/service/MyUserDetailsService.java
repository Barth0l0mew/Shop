package Shop.Shop.service;

import Shop.Shop.dao.MyUserRepository;
import Shop.Shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = myUserRepository.findByUsername(username);
        System.out.println("==loadUserByUsername=="+user.orElse(null));
        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username+" not found"));
    }
}
