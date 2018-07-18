package com.sample.integration.business;

import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ToFinish {

    @ServiceActivator(inputChannel = "anotherFilter")
    public void filter(Message<Product> payload) {
        // force all messages to be discarded to test discardChannel
        log.info("end of process");

    }

}
