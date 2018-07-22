package com.sample.integration.business;

import com.sample.integration.model.Product;
import com.sample.integration.model.ProductsWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ProductsMessageReceiver {

    private static final String ORDER_COMPTA_QUEUE = "order-compta-queue";

    @Autowired
    MessagingTemplate messagingTemplate;

    @JmsListener(destination = ORDER_COMPTA_QUEUE, selector = "source <> 'EMEA'")
    public void receiveMessage(Message<ProductsWrapper> productsMessage) {

        List<Product> products = productsMessage.getPayload().getProducts();
        MessageHeaders headers = productsMessage.getHeaders();

        log.info("products size = {}",products.size());
        log.info("headers = [{}]",headers);
        log.info("source = [{}]",headers.get("source"));

        messagingTemplate.send("productsToSplitChannel",productsMessage);

    }

}