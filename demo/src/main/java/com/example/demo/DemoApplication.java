package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public UserRepository buildDataRepository(PasswordEncoder encoder) {
		return UserRepository.builder()
				.users(List.of(
						User.builder().username("Ted").password(encoder.encode("passted")).build(),
						User.builder().username("Mary").password(encoder.encode("passmary")).build(),
						User.builder().username("Buck").password(encoder.encode("passbuck")).build()
				))
				.build();
	}
}
