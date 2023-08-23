package com.elr.bikeservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class BikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeServiceApplication.class, args);
	}

}
