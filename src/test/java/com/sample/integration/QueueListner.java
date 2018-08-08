package com.sample.integration;

import com.sample.integration.model.ProductsWrapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class QueueListner {

    private int messagesCounts;

    @JmsListener(destination = "order-compta-queue", selector = "source <> 'EMEA'")
    public void receiveMessage(Message<ProductsWrapper> productsMessage) {
        messagesCounts ++;
    }

    public int getMessagesCounts() {
        return messagesCounts;
    }

    public void setMessagesCounts(int messagesCounts) {
        this.messagesCounts = messagesCounts;
    }
}
