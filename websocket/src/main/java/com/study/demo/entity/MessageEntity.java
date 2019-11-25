package com.study.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tiger
 * @date 2019-10-29
 * @see com.study.demo.entity
 **/
@Data
public class MessageEntity implements Serializable {
    /**
     * 消息内容
     **/
    String message;
    /**
     * 谁发送的
     **/
    String fromUser;
    /**
     * 发给谁
     **/
    String toUser;
    /**
     * 发送类型，群发or私聊
     **/
    String to;
    /**
     * 房间编号
     **/
    String roomId;
    /**
     * 消息类型
     * 1代表上线 2代表下线 3代表在线名单 4代表普通消息
     **/
    int messageType;
    /**
     * 新用户上线名称
     **/
    String newUser;
    /**
     * 用户下线
     **/
    String offUser;
    /**
     * 用户列表
     **/
    List<String> userList;
}
