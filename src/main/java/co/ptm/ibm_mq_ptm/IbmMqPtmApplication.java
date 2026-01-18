package co.ptm.ibm_mq_ptm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class IbmMqPtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbmMqPtmApplication.class, args);
	}

}
