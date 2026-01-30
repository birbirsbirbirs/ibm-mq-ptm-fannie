package co.ptm.ibm_mq_ptm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.Message;

import java.util.function.Consumer;


@Slf4j
@EnableJms
@SpringBootApplication
public class IbmMqPtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbmMqPtmApplication.class, args);
    }

    @Bean
    public Consumer<Message<?>> mqConsumerOne() {
        return message -> log.info("mqConsumerOne received message: {}", message.getPayload().toString());
    }

    @Bean
    public Consumer<Message<?>> mqConsumerSecond() {
        return message -> log.info("mqConsumerSecond received message: {}", message.getPayload().toString());
    }

}
