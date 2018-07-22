package com.sample.integration.business.splitter;

import com.sample.integration.model.Product;
import com.sample.integration.model.ProductsWrapper;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsSplitter {

    @Splitter(inputChannel = "productsToSplitChannel",outputChannel = "productsRoutingChannel")
    public List<Message<Product>> splitProducts(Message<ProductsWrapper> products){

        return products.getPayload().getProducts()
                .stream()
                .map(p -> MessageBuilder.createMessage(p,products.getHeaders()))
                .collect(Collectors.toList());
    }
}
