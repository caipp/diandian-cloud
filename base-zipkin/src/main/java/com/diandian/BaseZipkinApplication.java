package com.diandian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * @author caipiaoping
 */
@SpringBootApplication
@EnableZipkinStreamServer
public class BaseZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseZipkinApplication.class, args);
    }

}
