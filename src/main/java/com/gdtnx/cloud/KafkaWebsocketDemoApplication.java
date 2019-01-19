package com.gdtnx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gdtnx.cloud")
public class KafkaWebsocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaWebsocketDemoApplication.class, args);
	}

}

