package com.sample.integration.business;

import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageFilter2 {

    @Filter(inputChannel = "usRoutedChannel")
    public boolean filter(Message<Product> payload) {
        // force all messages to be discarded to test discardChannel
        log.info("filter level2");
        return false;
    }
}
