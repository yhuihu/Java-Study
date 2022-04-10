package com.study.demo.service;

import org.jgroups.*;
import org.jgroups.stack.AddressGenerator;
import org.jgroups.util.ExtendedUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class JGroupsService implements Receiver, ChannelListener {

    private Logger logger = LoggerFactory.getLogger(JGroupsService.class);

    private volatile JChannel jChannel = null;

    @PostConstruct
    void init() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("JGroups_JDBC.xml");
        jChannel = new JChannel(inputStream);
        jChannel.setReceiver(this);
        jChannel.addChannelListener(this);
        byte[] ip;
        try {
            ip = InetAddress.getLocalHost().getAddress();
        } catch (UnknownHostException e) {
            throw new Exception("init error");
        }
        jChannel.addAddressGenerator(new AddressGenerator() {
            @Override
            public Address generateAddress() {
                ExtendedUUID extendedUUID = ExtendedUUID.randomUUID();
                extendedUUID.put("ip", ip);
                extendedUUID.put("uuid", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
                extendedUUID.put("sys", "TEST".getBytes(StandardCharsets.UTF_8));
                return extendedUUID;
            }
        });
        jChannel.connect("test_cluster");
        jChannel.getState(null, 50000);

    }

    @PreDestroy
    void story() {
        if (jChannel != null) {
            jChannel.close();
        }
    }

    @Override
    public void receive(Message msg) {
        logger.info("收到消息：{}", msg);
    }

    @Override
    public void viewAccepted(View new_view) {
        logger.info("视图发生变化：{}", new_view);
    }


    @Override
    public void channelConnected(JChannel channel) {
        logger.info("连接上了：{}", channel);
        try {
            byte[] ip = InetAddress.getLocalHost().getAddress();
            channel.send(new Message(null, ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelDisconnected(JChannel channel) {
        logger.info("断线了：{}", channel);
    }

    @Override
    public void channelClosed(JChannel channel) {
        logger.info("连接关闭了：{}", channel);
    }
}
