package com.sifan.livegoods.message.publisher;

import com.sfian.livegoods.message.LivegoodsMessage;
import org.springframework.amqp.core.AmqpTemplate;

// 消息发送工具
public class LivegoodsMessagePublisher {

    private AmqpTemplate amqpTemplate;

    /**
     * 同步消息发送方法。 消息的消费者不返回，当前方法不结束
     * @param exchange 交换器名称
     * @param routingKey 路由键
     * @param message 要发送的消息对象
     * @return
     */
    public Object sendMessageSync(String exchange, String routingKey, LivegoodsMessage message){
        return amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
    }

    /**
     * 异步消息发送方法。 消息发送立刻返回。不等待消费者处理。
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendMessageAsync(String exchange, String routingKey, LivegoodsMessage message){
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }
}
