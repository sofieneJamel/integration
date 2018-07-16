package com.sample.integration.business;

import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageFilter {

    @Filter(inputChannel = "channelOutput",outputChannel = "anotherFilter")
    public boolean filter(Message<Product> payload) {
        // force all messages to be discarded to test discardChannel
        System.out.println("filter level");
        return true;
    }

}