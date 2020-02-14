package com.ZooManagerApp;

import com.ZooManagerApp.configuration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ZooManagerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZooManagerAppApplication.class, args);
	}

	@Bean
	public Docket getDocket () {
		return new SwaggerConfiguration().getSwaggerConfiguration();
	}
}
