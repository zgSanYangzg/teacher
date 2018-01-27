package org.tyrest.logger.kafka;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component(value = "kafkaInitializer")
public class KafkaInitializer implements InitializingBean{
	
	@Autowired
	private KafkaConsumer kc;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Thread th = new Thread(kc);
		th.start();
	}
}

/*
*$Log: av-env.bat,v $
*/