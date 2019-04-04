package com.wwn.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DefaultProcess {

    public static final String OUTPUT = "enjoy_output"; // 输出通道名称
    public static final String INPUT = "enjoy_input"; // 输入通道名称
    @Input(DefaultProcess.INPUT)
    public SubscribableChannel input();
    @Output(DefaultProcess.OUTPUT)
    public MessageChannel output();

}
