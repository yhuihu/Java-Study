package com.study.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.demo.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tiger
 * @date 2019-10-28
 * @see com.study.demo.web
 **/
@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {
    private static ConcurrentHashMap<String, Set<WebSocketSession>> roomUserMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, WebSocketSession> userSession = new ConcurrentHashMap<>();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.fromUri(session.getUri()).build();
        String roomId = uriComponents.getQueryParams().getFirst("roomId");
        String userName = uriComponents.getQueryParams().getFirst("userName");
        String key = "room:" + roomId;
        assert userName != null;
        if (userSession.get(userName) != null) {
            userSession.remove(userName);
        }
        //存一个用户session对应map，私聊用
        userSession.put(userName, session);
//        如果这个房间没有set集合，则创建一个新的set集合
        roomUserMap.putIfAbsent(key, new HashSet<>());
        assert roomId != null;
        roomUserMap.get(key).add(session);
        log.info("新用户来了：{}", session);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageType(1);
        messageEntity.setNewUser(userName);
        messageEntity.setUserList(getUserList(userSession));
        ObjectMapper mapper = new ObjectMapper();
        TextMessage returnMessage = new TextMessage(mapper.writeValueAsString(messageEntity));
        for (WebSocketSession webSocketSession : roomUserMap.get(key)) {
            webSocketSession.sendMessage(returnMessage);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.fromUri(session.getUri()).build();
        String roomId = uriComponents.getQueryParams().getFirst("roomId");
        String key = "room:" + roomId;
        String userName = uriComponents.getQueryParams().getFirst("userName");

        assert roomId != null;
        roomUserMap.get(key).remove(session);
        assert userName != null;
        userSession.remove(userName);

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageType(2);
        messageEntity.setOffUser(userName);
        messageEntity.setUserList(getUserList(userSession));

        ObjectMapper mapper = new ObjectMapper();
        TextMessage returnMessage = new TextMessage(mapper.writeValueAsString(messageEntity));
        for (WebSocketSession webSocketSession : roomUserMap.get(key)) {
            webSocketSession.sendMessage(returnMessage);
        }
        log.info("用户走了：{}", session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MessageEntity messageEntity = mapper.readValue(message.getPayload(), MessageEntity.class);
//        if (messageEntity.getTo().equals("ALL")) {
//            for (WebSocketSession webSocketSession : roomUserMap.get("room:" + messageEntity.getRoomId())) {
//                webSocketSession.sendMessage(message);
//            }
//        } else {
//            WebSocketSession webSocketSession = userSession.get(messageEntity.getTo());
//            webSocketSession.sendMessage(message);
//        }
        redisTemplate.convertAndSend("TEST_CHANNEL", messageEntity);
    }

    private List<String> getUserList(ConcurrentHashMap<String, WebSocketSession> concurrentHashMap) {
        List<String> userList = new ArrayList<>();
        concurrentHashMap.forEach((item, item1) -> {
            userList.add(item);
        });
        return userList;
    }

    public Boolean sendToUser(String userName, String message) throws IOException {
        WebSocketSession webSocketSession = userSession.get(userName);
        if (webSocketSession != null) {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setMessageType(2);
            messageEntity.setOffUser(userName);
            messageEntity.setUserList(getUserList(userSession));

            ObjectMapper mapper = new ObjectMapper();
            TextMessage returnMessage = new TextMessage(mapper.writeValueAsString(messageEntity));
            try {
                webSocketSession.sendMessage(returnMessage);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static Boolean redisSendToUser(MessageEntity messageEntity) throws IOException {
        WebSocketSession webSocketSession = userSession.get(messageEntity.getTo());
        if (webSocketSession != null) {
            ObjectMapper mapper = new ObjectMapper();
            TextMessage returnMessage = new TextMessage(mapper.writeValueAsString(messageEntity));
            try {
                webSocketSession.sendMessage(returnMessage);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
