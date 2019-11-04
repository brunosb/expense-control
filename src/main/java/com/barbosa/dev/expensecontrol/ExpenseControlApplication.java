package com.barbosa.dev.expensecontrol;

import com.barbosa.dev.expensecontrol.enums.RoleName;
import com.barbosa.dev.expensecontrol.model.Permission;
import com.barbosa.dev.expensecontrol.model.UserSystem;
import com.barbosa.dev.expensecontrol.repository.PermissionRepository;
import com.barbosa.dev.expensecontrol.repository.UserSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
@EntityScan
public class ExpenseControlApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseControlApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExpenseControlApplication.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserSystemRepository userSystemRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!permissionRepository.existsByName(RoleName.ADMIN)) {
            permissionRepository.save(Permission.builder().name(RoleName.ADMIN).build());
        }
        String login = "brunosb";
        String password = "bruno123";
        if (!userSystemRepository.existsByLogin(login)) {
            userSystemRepository.save(UserSystem.builder()
                    .login(login)
                    .password(passwordEncoder.encode(password))
                    .permissoes(Collections.singleton(permissionRepository.findByName(RoleName.ADMIN).orElse(null)))
                    .email("barbosa.developer@gmail.com")
                    .nome("Barbosa")
                    .build());
        }
    }
}
