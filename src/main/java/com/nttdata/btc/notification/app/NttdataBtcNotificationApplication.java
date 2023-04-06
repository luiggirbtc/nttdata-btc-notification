package com.nttdata.btc.notification.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NttdataBtcNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NttdataBtcNotificationApplication.class, args);
	}

}