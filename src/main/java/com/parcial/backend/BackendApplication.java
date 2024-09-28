package com.parcial.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.parcial.backend.model.entity.Users;
import com.parcial.backend.model.repository.UsersRepository;
import com.parcial.backend.security.auth.Role;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder){
		return args -> {
			if(usersRepository.count()==0){
				Users admin = Users.builder()
					.name("Admin")
					.email("admin@gmail.com")
					.password(passwordEncoder.encode("12345"))
					.profilePic(null)
					.role(Role.ADMIN)
					.build();
				usersRepository.save(admin);
				System.out.println("Default admin user created "+admin.getName());
			}
		};
	}
}
