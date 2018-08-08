package com.sample.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,classes = IntegrationApplication.class)
public class IntegrationApplicationTest {
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	QueueListner queueListner;

	@Test
	public void contextLoads() {
		IntegrationApplication.main(new String[]{});
		await().atMost(5,SECONDS).until(checkMessages());
		assertThat(queueListner.getMessagesCounts(),is(3));
	}

	private Callable<Boolean> checkMessages() {
		return () -> queueListner.getMessagesCounts() == 3;
	}

}
