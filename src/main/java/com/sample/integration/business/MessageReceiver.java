package com.sample.integration.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final String ORDER_COMPTA_QUEUE = "order-compta-queue";

    @Autowired
    MessagingTemplate messagingTemplate;

    @JmsListener(destination = ORDER_COMPTA_QUEUE, selector = "source = 'Europe' or source = 'EMEA'")
    public void receiveMessage(Message<Product> productMessage) {
        Product product = productMessage.getPayload();
        MessageHeaders headers = productMessage.getHeaders();
        System.out.println("product = [" + product + "]");
        System.out.println("headers = [" + headers + "]");
        System.out.println("source = [" + headers.get("source") + "]");

        messagingTemplate.send("channelOutput",productMessage);

    }

}