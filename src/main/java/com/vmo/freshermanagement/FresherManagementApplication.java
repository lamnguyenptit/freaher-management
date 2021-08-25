package com.vmo.freshermanagement;

import com.vmo.freshermanagement.model.User;
import com.vmo.freshermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FresherManagementApplication{
    public static void main(String[] args) {
        SpringApplication.run(FresherManagementApplication.class, args);
    }

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        User user = new User();
//        user.setUsername("lam");
//        user.setPassword(passwordEncoder.encode("123456"));
//        userRepository.save(user);
//        System.out.println(user);
//    }
}
