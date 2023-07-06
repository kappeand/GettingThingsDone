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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

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
        u.setLoginName("user");
        u.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
        Role r = new Role();
        r.setRoleName("ROLE_USER");
        roleRepository.save(r);
        u.getRoles().add(r);
        userRepository.save(u);

        Project inbox = new Project("Inbox", null, u);
        projectRepository.save(inbox);

        Project work = new Project("Work", null, u);
        projectRepository.save(work);


        Task t1 = new Task("Work Task 1", "Description1", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .parse("2017-11-15 15:30:14.332"),
                1, false, work.getId());
        taskRepository.save(t1);

        Task t2 = new Task("Work Task 2", "Description2", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .parse("2017-11-15 15:30:14.332"), 2, false, work.getId());
        taskRepository.save(t2);

        Task t3 = new Task("Inbox Task 3", "Description3", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .parse("2017-11-15 15:30:14.332"), 2, false, inbox.getId());
        taskRepository.save(t3);

        Task t4 = new Task("Inbox Task 4", "Description4", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .parse("2017-11-15 15:30:14.332"), 2, false, inbox.getId());
        taskRepository.save(t4);

        Task t5 = new Task("Inbox Task 5", "Description5", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .parse("2017-11-15 15:30:14.332"), 3, true, inbox.getId());
        taskRepository.save(t5);

        Task t6 = new Task("Inbox Task 6", "Description6", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .parse("2017-11-15 15:30:14.332"), 3, false, work.getId());
        taskRepository.save(t6);

        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t6);

        work.setTasks(tasks);

        projectRepository.save(work);
    }
}
