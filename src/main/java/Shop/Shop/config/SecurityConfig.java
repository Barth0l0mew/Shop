package Shop.Shop.config;

//import Shop.Shop.model.User;
import Shop.Shop.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
        import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    //public PasswordEncoder passwordEncoder= passwordEncoder(); ;
    @Bean
    public UserDetailsService userDetailsService() {

        return new MyUserDetailsService();
    }
    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        //passwordEncoder - кодировщик
        return new BCryptPasswordEncoder(8);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/",
                                "/index",
                                "/filter",
                                "/h2-console/**",
                                "/css/**",
                                "/registration",
                                "/register",
                                "/image/**",
                                "/uploaded/**",
                                "/product"
                                ).permitAll()
                        .requestMatchers("/admin/**",
                                "admin/editUser",
                                "/admin/product/**",
                                "/static/css/uploaded/**",
                                "/static/uploaded/**",
                                "/uploaded/**",
                                "/admin/product/productedit",
                                "/admin/order/**",
                                "/buy/**",
                                "/buy/product",
                                "/basket",
                                "/deleteproduct",
                                "/order",
                                "/send").authenticated())
                //.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .formLogin(form->form
                        .loginPage("/login")
                        .loginProcessingUrl("/log")
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .build();

    }
}
