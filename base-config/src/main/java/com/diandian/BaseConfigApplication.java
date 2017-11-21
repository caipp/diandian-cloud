package com.diandian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-11-04
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class BaseConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseConfigApplication.class, args);
    }
}
