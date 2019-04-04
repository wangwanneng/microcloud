package com.wwn.service.impl;

import com.wwn.channel.DefaultProcess;
import com.wwn.service.IMessageProvider;
import com.wwn.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

//@EnableBinding(Source.class)
//自定义通道
@EnableBinding(DefaultProcess.class)
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    @Qualifier("enjoy_output")
    private MessageChannel output;  // 消息的发送管道

    @Override
    public void send(Product product) {
        output.send(MessageBuilder.withPayload(product).build());
    }
}
