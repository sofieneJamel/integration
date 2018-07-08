package com.sample.integration.business;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final String ORDER_COMPTA_QUEUE = "order-compta-queue";

    @JmsListener(destination = ORDER_COMPTA_QUEUE)
    public void receiveMessage(final Message<String> message) throws InterruptedException {
        System.out.println("message = [" + message.getPayload() + "]");
        MessageHeaders headers = message.getHeaders();
    }

}