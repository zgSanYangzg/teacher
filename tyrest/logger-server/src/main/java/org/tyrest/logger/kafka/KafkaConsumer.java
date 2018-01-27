package org.tyrest.logger.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.logger.repository.LogDAO;
import org.tyrest.logger.repository.LogHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * <pre>
 * 
 *  File: KafkaConsumer.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  KafkaConsumer.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日					magintursh				   Initial.
 *
 * </pre>
 */
@Component(value = "kafkaConsumer")
public class KafkaConsumer implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@Value("${kafka.topic.log}")
	private String topic;

	@Autowired
	private LogDAO logDAO;
	
	private Properties configProp;
	
	@Autowired
	private LogHandler logHandler;
	
	public KafkaConsumer() {
		configProp = new Properties();
		try {
			configProp.load(this.getClass().getClassLoader().getResourceAsStream("tyrest-kafka.properties"));
			if(logger.isDebugEnabled()){
				logger.debug("kafka config has been loaded successfully!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		ConsumerConfig config = new ConsumerConfig(configProp);
		ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap,
				keyDecoder, valueDecoder);
		KafkaStream<String, String> stream = consumerMap.get(topic).get(0);
		ConsumerIterator<String, String> it = stream.iterator();
		while (it.hasNext()) {
			logHandler.doLog(it.next().message());
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */