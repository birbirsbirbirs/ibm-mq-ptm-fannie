package co.ptm.ibm_mq_ptm.binder;

import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class PtmMqMessageProducer extends MessageProducerSupport {
    private final ConsumerDestination destination;
    private String previousPayload;
    private final JmsTemplate jmsTemplate;

    public PtmMqMessageProducer(ConsumerDestination destination, JmsTemplate jmsTemplate) {
        this.destination = destination;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    protected void doStart() {
        receive();
    }

    private void receive() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> {
            String payload=getPayload();

            if(payload != null){
                Message<String> receiveMessage= MessageBuilder.withPayload(payload).build();
                sendMessage(receiveMessage);
            }

        }, 0, 50, MILLISECONDS);
    }

    private String getPayload() {
        return jmsTemplate.receiveAndConvert(destination.getName()).toString();
    }


}
