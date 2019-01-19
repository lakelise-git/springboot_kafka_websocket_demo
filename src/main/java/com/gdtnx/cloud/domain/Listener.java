package com.gdtnx.cloud.domain;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.gdtnx.cloud.WbSocketConfig;
@Component
public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
   
    private Session session;
    private WbSocketConfig wbSocket ;
   
    @Autowired
    public void setWbSocket(WbSocketConfig wbSocket) {
		this.wbSocket = wbSocket;
	}
    
    @KafkaListener(topics = {"test"})
    public void consumerMessage(String message) {
    	
        logger.info("on message:{}", message);

        try {
//        	((Object) endpoint).getEndpointInstance(WbSocketConfig.class);
        	
//        	Field[] declaredFields = WbSocketConfig.class.getDeclaredFields();
//        	for(Field obj : declaredFields) {
//        		if(obj.getName().equals("session")) {
//        			try {
//						Class clazz = Class.forName("com.gdtnx.cloud。WbSocketConfig");
//						
//						try {
//							Method m = clazz.getDeclaredMethod("sendMessage", String.class);
//							m.invoke(message);
//							
//						} catch (NoSuchMethodException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (SecurityException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						
//						} catch (IllegalAccessException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IllegalArgumentException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (InvocationTargetException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//					} catch (ClassNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//        			System.out.println("safda");
//        		}
//        	}
        	
        	CopyOnWriteArraySet<WbSocketConfig> wbSockets = WbSocketConfig.wbSockets;
        	Object[] array = wbSockets.toArray();
        	WbSocketConfig r = (WbSocketConfig)array[0];
//        	if(r.getSession() != null) {
//        		session = r.getSession();
//        		session.getBasicRemote().sendText(message);
//        	}
//        	if(session != null) {
//        		session.getBasicRemote().sendText(message);
//        	}
        	
        	r.sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("消费数据==="+message);
    }

//    @KafkaListener(topics = {"test01"})
//    public void listen(ConsumerRecord<?, ?> record) {
//        logger.info("kafka的key: " + record.key());
//        logger.info("kafka的value: " + record.value().toString());
//    }
}