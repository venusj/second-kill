package com.venusj.secondkill.controller;

import com.venusj.secondkill.config.RabbitConfig;
import com.venusj.secondkill.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

/**
 * @author zhangjh
 * @date 2020/8/27
 * @desc
 */
@RestController
@RequestMapping("/mq")
@Slf4j
public class MqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @GetMapping("/sendDlx")
    public void sendDlx()
    {
        User user = new User();
        user.setId(1);
        user.setName("测试死信队列");
        // 添加延迟队列
        rabbitTemplate.convertSendAndReceive(RabbitConfig.REGISTER_DELAY_EXCHANGE, RabbitConfig.DELAY_ROUTING_KEY, user , message -> {
            // TODO 第一句是可要可不要,根据自己需要自行处理
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Book.class.getName());
            // TODO 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
    }
}


