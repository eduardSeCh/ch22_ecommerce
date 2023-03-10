package org.generation.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ch22EcommerceApplication {

	public String PORT = System.getenv("PORT");
	
	public static void main(String[] args) {
		SpringApplication.run(Ch22EcommerceApplication.class, args);
	}

    //Scaneo de metodos
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
