package com.valam.app;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.valam.app.config.AppProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableSwagger2
public class ValamWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValamWebAppApplication.class, args);		
		}
}
