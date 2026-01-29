package co.ptm.ibm_mq_ptm.binder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class PtmMQConfiguration {

    @Bean
    public MqConfigBinderProvisioner mqConfigBinderProvisioner(){
        return new MqConfigBinderProvisioner();
    }

    @Bean
    public PtmMqMessageBinder ptmMqMessageBinder(MqConfigBinderProvisioner mqConfigBinderProvisioner,
                                                 JmsTemplate jmsTemplate){
        return new PtmMqMessageBinder(null,mqConfigBinderProvisioner,jmsTemplate);
    }
}
