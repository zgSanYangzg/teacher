package org.tyrest.core.logger;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: KafkaProducer.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:KAFKA消息生产者
 *  TODO
 * 
 *  Notes:
 *  $Id: KafkaProducer.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "kafkaProducer")
public class KafkaProducer
{
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	
	private Producer<String, String>  producer = null;
	
	@Value("${kafka.topic.log}")
	private String topic;
	
	public KafkaProducer(){
		Properties props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("tyrest-kafka.properties"));
			if(logger.isDebugEnabled()){
				logger.debug("kafka config has been loaded successfully!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		producer = new Producer<String, String>(new ProducerConfig(props));
	}
	
	@Async
	public void send(final String message)
	{
		String key = String.valueOf(1000);
		producer.send( new KeyedMessage<String, String>(topic, key ,message));
	}
}
/*
 * $Log: av-env.bat,v $
 */