package com.proto.linksaver;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class LinksaverApplication {
	public static void main(String[] args) {
		SpringApplication.run(LinksaverApplication.class, args);
	}
}
