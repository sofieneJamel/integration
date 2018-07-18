package com.sample.integration.business;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageReceiver {

    private static final String ORDER_COMPTA_QUEUE = "order-compta-queue";

    @Autowired
    MessagingTemplate messagingTemplate;

    @JmsListener(destination = ORDER_COMPTA_QUEUE, selector = "source <> 'EMEA'")
    public void receiveMessage(Message<Product> productMessage) {
        Product product = productMessage.getPayload();
        MessageHeaders headers = productMessage.getHeaders();

        log.info("product = {}",product);
        log.info("headers = [{}]",headers);
        log.info("source = [{}]",headers.get("source"));

        messagingTemplate.send("productsToProcessChannel",productMessage);

    }

}