package ch.zhaw.students.gtd;

import ch.zhaw.students.gtd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class Backend implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Backend.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskRepository toDoRepository;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // This is only really relevant for development, where we have different servers for frontend and backend
                        .allowedOrigins("http://localhost:8100")
                        .allowedMethods("GET", "PUT", "POST", "DELETE")
                        // AllowCredentials is necessary, as it sets 'Access-Control-Allow-Credentials'.
                        // Otherwise Angular's HttpClient will not pass the Cookie back.
                        .allowCredentials(true);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        User u = new User();
        u.setName("user");
        u.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
        Role r = new Role();
        r.setName("USER");
        roleRepository.save(r);
        u.getRoles().add(r);
        userRepository.save(u);
    }
}
