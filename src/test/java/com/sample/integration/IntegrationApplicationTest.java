package com.sample.integration;

import com.sample.integration.business.splitter.ProductsSplitter;
import com.sample.integration.config.ApplicationTestConfig;
import com.sample.integration.model.Product;
import com.sample.integration.model.ProductsWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTestConfig.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationApplicationTest {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	QueueListner queueListner;

	@Autowired
	ProductsSplitter productsSplitterSpyed;

	@Test
	public void contextLoads() {
		jmsSender();
		await().atMost(5,SECONDS).until(checkMessages());
		assertThat(queueListner.getMessagesCounts(),is(3));
	}


	@Test
	public void should_call_splitProducts_when_valide_message_is_sent() {
		jmsSender();
		await().atMost(5,HOURS).until(
				() -> {
					try {
						verify(productsSplitterSpyed, atLeast(1)).splitProducts(any());
						return true;
					} catch (WantedButNotInvoked e) {
						return false;
					}
				}
		);

	}

	private Callable<Boolean> checkMessages() {
		return () -> queueListner.getMessagesCounts() == 3;
	}


	private void jmsSender() {
		jmsTemplate.convertAndSend("order-compta-queue",
				new ProductsWrapper().setProducts(Collections.singletonList(new Product("code", "label", 120, 25, "EUROPE"))),
				messagePostProcessor -> {
					messagePostProcessor.setStringProperty("source", "Europe");
					return messagePostProcessor;

				});

		jmsTemplate.convertAndSend("order-compta-queue",
				new ProductsWrapper().setProducts(Collections.singletonList(new Product("code", "label", 120, 25, "EUROPE"))),
				messagePostProcessor -> {
					messagePostProcessor.setStringProperty("source", "United state");
					return messagePostProcessor;

				});

		jmsTemplate.convertAndSend("order-compta-queue",
				new ProductsWrapper().setProducts(Collections.singletonList(new Product("code", "label", 120, 25, "EUROPE"))),
				messagePostProcessor -> {
					messagePostProcessor.setStringProperty("source", "ASIA");
					return messagePostProcessor;

				});
	}
}
