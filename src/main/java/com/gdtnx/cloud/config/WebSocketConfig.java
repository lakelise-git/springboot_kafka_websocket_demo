package com.gdtnx.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.gdtnx.cloud.WbSocketConfig;

@Configuration
public class WebSocketConfig {
	@Bean
    public ServerEndpointExporter serverEndpointExporter(){
		
		ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        serverEndpointExporter.setAnnotatedEndpointClasses(WbSocketConfig.class);
        return serverEndpointExporter;
	}
}
