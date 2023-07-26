package com.shirley.hxmall.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/webSocket/{oid}")
public class WebSocketServer {

    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**前端发送请求建立websocket连接，就会执行@OnOpen方法**/
    @OnOpen
    public void open(@PathParam("oid") String orderId, Session session){
        System.out.println("------------建立连接："+orderId);
        sessionMap.put(orderId,session);
    }

    /**前端关闭页面或者主动关闭websocket连接，都会执行close**/
    @OnClose
    public void close(@PathParam("oid") String orderId){
        sessionMap.remove(orderId);
    }

    public static void sendMsg(String orderId,String msg){
        try {
            Session session = sessionMap.get(orderId);
            session.getBasicRemote().sendText(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
