package co.ptm.ibm_mq_ptm.controller;

import co.ptm.ibm_mq_ptm.model.Hero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/hero")
public class HeroController {

    private final JmsTemplate jmsTemplate;
    private final StreamBridge streamBridge;

    @PostMapping
    public Hero publishHero(@RequestBody Hero hero) {

        try{
            log.info("publishHero: {}", hero);
            jmsTemplate.convertAndSend("DEV.QUEUE.1", hero.toString());
            return hero;
        }catch(JmsException ex){
            ex.printStackTrace();
            return null;
        }

    }

    @PostMapping("/second")
    public String publishHeroSecond(@RequestBody Hero hero) {

        try{
            log.info("publishHero: {}", hero);
//            jmsTemplate.convertAndSend("DEV.QUEUE.2", hero.toString());
            jmsTemplate.convertAndSend("ptm", hero.toString());
            return "OK";
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }

    }

    @GetMapping("hero-receive")
    String recv(){
        try{
            String receivedMessage = jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
            log.info("recv: {}", receivedMessage);
            return receivedMessage;
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }

    @PostMapping("/stream/publish-hero")
    public boolean streamPublishHero(@RequestBody Hero hero) {

        boolean isSend = streamBridge.send("mqProducer-out-0", hero.toString());
        return isSend;
    }
}
