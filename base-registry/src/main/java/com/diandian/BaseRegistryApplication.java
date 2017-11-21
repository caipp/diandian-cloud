package com.diandian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author caipiaoping
 */
@SpringBootApplication
@EnableEurekaServer
public class BaseRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseRegistryApplication.class, args);
	}
}
