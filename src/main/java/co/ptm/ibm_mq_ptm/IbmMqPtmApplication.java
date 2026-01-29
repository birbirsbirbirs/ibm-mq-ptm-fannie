package co.ptm.ibm_mq_ptm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;


@Slf4j
@EnableJms
@SpringBootApplication
public class IbmMqPtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbmMqPtmApplication.class, args);
    }

//    @JmsListener(destination = "DEV.QUEUE.2")
    @JmsListener(destination = "ptm")
    public void receiveMessage(String message) {
        log.info("received message: {}", message);
    }

}
