package com.venusj.secondkill.consumer;

import com.rabbitmq.client.Channel;
import com.venusj.secondkill.config.RabbitConfig;
import com.venusj.secondkill.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zhangjh
 * @date 2020/8/27
 * @desc
 */
@Component
@Slf4j
public class UserConsumer {

    @RabbitListener(queues = {RabbitConfig.REGISTER_QUEUE_NAME})
    public void listenerDelayQueue(User user, Message message, Channel channel) {
        log.info("延迟队列 REGISTER_DELAY_QUEUE   超时了，然后就转发到REGISTER_QUEUE_NAME队列");
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now(), user.toString());
        try {
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }
}
