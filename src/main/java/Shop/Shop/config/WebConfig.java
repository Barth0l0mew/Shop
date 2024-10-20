package Shop.Shop.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded/**")
                //.addResourceLocations("classpath:/static/uploaded/" )
                .addResourceLocations("file:src/main/resources/static/uploaded/")
                .setCacheControl(CacheControl.noCache());
    }
}