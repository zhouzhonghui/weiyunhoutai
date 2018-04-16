package io.renren.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;


@Configuration
public class ActiveMqConfig {
    @Bean(name="sampleQueue")
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }
    @Bean(name="scalpQueue")
    public Queue queue1() {
        return new ActiveMQQueue("scalp.queue");
    }
}
