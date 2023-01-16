package com.country.countryside.utils;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/server/{userId}")
@Component
public class WebSocketUtils {

    /**
     * 统计在线数
     */
    private static Integer onlineCount = 0;
    /**
     * 保存session
     */
    private static final ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 创建websocket链接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) throws IOException {
        String sessionId = session.getId() + "_" + userId;
        if(sessionMap.get(sessionId) == null){
            sessionMap.put(sessionId, session);
            synchronized (onlineCount) {
                onlineCount++;
            }
        }
        sendMessage(userId + "链接成功", session);
    }

    /**
     * 关闭websocket链接
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        String sessionId = session.getId();
        for(String entity : sessionMap.keySet()){
            if(entity.contains(sessionId)){
                sessionMap.remove(sessionMap.get(entity));
                synchronized (onlineCount) {
                    onlineCount++;
                }
                break;
            }
        }
    }

    /**
     * 接收到消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("接收到消息：" + message);
        sendMessage("后端接收到消息", session);
    }

    /**
     * 错误触发
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){

    }

    /**
     * 发送消息
     * @param message
     * @param session
     */
    public void sendMessage(String message, Session session) throws IOException {
        if(session == null){
            return;
        }
        session.getBasicRemote().sendText(message);
    }
}
