package com.sample.integration.business;

import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageFilter2 {

    @Filter(inputChannel = "anotherFilter")
    public boolean filter(Message<Product> payload) {
        // force all messages to be discarded to test discardChannel
        System.out.println("filter level2");
        return false;
    }
}
