package com.sample.integration;


import com.sample.integration.business.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class IntegrationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(IntegrationApplication.class);
		JmsTemplate jmsTemplate = configurableApplicationContext.getBean(JmsTemplate.class);
		jmsTemplate.convertAndSend("order-compta-queue",
				new Product("code","label",120,25,"EUROPE"),
				messagePostProcessor -> {
					messagePostProcessor.setStringProperty("source","Europe");
					return messagePostProcessor;

				});

		jmsTemplate.convertAndSend("order-compta-queue",
				new Product("code","label",120,25,"EUROPE"),
				messagePostProcessor -> {
					messagePostProcessor.setStringProperty("source","United state");
					return messagePostProcessor;

				});
	}
}
