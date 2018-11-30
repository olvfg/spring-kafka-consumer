package com.ibm.conskafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConsKafkaApplication {
	
	List<String> messages = new ArrayList<>();
	
	User userFromTopic = null;
	
	@GetMapping("/consumeStringMessages")
	public List<String> consumeMessages() {
		return messages;
	}
	
	@GetMapping("/consumeJsonMessages")
	public User consumeJsonMessage() {
		return userFromTopic;
	}
	
	@KafkaListener(groupId="kafka-teste", topics="helloww", containerFactory="kafkaListenerContainerFactory")
	public List<String> getMessageFromTopic(String data){
		messages.add(data);
		return messages;
	}
	
	//json
	@KafkaListener(groupId="kafka-teste-2", topics="helloww", containerFactory="userKafkaListenerContainerFactory")
	public User getJsonMessageFromTopic(User user){
		userFromTopic = user;
		return userFromTopic;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsKafkaApplication.class, args);
	}
}
