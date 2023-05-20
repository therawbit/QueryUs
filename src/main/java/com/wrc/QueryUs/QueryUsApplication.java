package com.wrc.QueryUs;

import com.wrc.QueryUs.repository.UserRepository;
import com.wrc.QueryUs.security.QueryUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QueryUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryUsApplication.class, args);
	}
	@Bean
	public QueryUtils queryUtils(UserRepository userRepository){
		return new QueryUtils(userRepository);
	}

}
