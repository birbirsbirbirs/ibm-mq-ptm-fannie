package co.ptm.ibm_mq_ptm.binder;

import org.springframework.cloud.stream.binder.AbstractMessageChannelBinder;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.integration.core.MessageProducer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

public class PtmMqMessageBinder extends AbstractMessageChannelBinder<ConsumerProperties,
        ProducerProperties,MqConfigBinderProvisioner> {

    private final JmsTemplate jmsTemplate;


    public PtmMqMessageBinder(
            String[] headersToEmbed,
            MqConfigBinderProvisioner mqConfigBinderProvisioner,
            JmsTemplate jmsTemplate){
        super(headersToEmbed,mqConfigBinderProvisioner);
        this.jmsTemplate=jmsTemplate;
    }

    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination,
                                                          ProducerProperties producerProperties,
                                                          MessageChannel errorChannel) throws Exception {
        return message -> {
//            String name = destination.getName();
            String payload = new String((byte[]) message.getPayload());
            jmsTemplate.convertAndSend(payload);
        };
    }

    @Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination,
                                                     String group,
                                                     ConsumerProperties properties) throws Exception {
        return new PtmMqMessageProducer(destination,jmsTemplate);
    }


}
