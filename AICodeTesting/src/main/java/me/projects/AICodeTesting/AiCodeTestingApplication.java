package me.projects.AICodeTesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "me.projects.AICodeTesting.Service.External")
public class AiCodeTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiCodeTestingApplication.class, args);
	}

}
