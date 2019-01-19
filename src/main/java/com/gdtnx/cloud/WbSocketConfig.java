package com.gdtnx.cloud;



import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @project_name: kafka_websocket_demo
 * @package: com.gdtnx.cloud.kafka_websocket_demo.config
 * @description : TODO
 * @authur:HHJ
 * @create_date:2019/1/16 21:09
 * 2019 PD.Sp Center . GDT Co.Ltd  -版权所有
 */
@ServerEndpoint(value = "/testsocket")
@Component
public class WbSocketConfig {

    private Session session;
    public static CopyOnWriteArraySet<WbSocketConfig> wbSockets = new CopyOnWriteArraySet<WbSocketConfig>(); //此处定义静态变量，以在其他方法中获取到所有连接

    /**
     * 建立连接。
     * 建立连接时入参为session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        wbSockets.add(this); //将此对象存入集合中以在之后广播用，如果要实现一对一订阅，则类型对应为Map。由于这里广播就可以了随意用Set
        System.out.println("New session insert,sessionId is "+ session.getId());
    }
    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        wbSockets.remove(this);//将socket对象从集合中移除，以便广播时不发送次连接。如果不移除会报错(需要测试)
        System.out.println("A session insert,sessionId is "+ session.getId());
    }
    /**
     * 接收前端传过来的数据。
     * 虽然在实现推送逻辑中并不需要接收前端数据，但是作为一个webSocket的教程或叫备忘，还是将接收数据的逻辑加上了。
     */
    @OnMessage
    public void onMessage(String message ,Session session){
        System.out.println(message + "from " + session.getId());
    }
//    @KafkaListener(topics = {"test"})
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
//    public Session getSession() {
//		return session;
//	}
//    public void setSession(Session session) {
//		this.session = session;
//	}
}
