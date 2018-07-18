package com.sample.integration.business;

import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ProductMessagesRouter {


    @Router(inputChannel = "productsToProcessChannel")
    public String router(Message<Product> productMessage) {
        String source = (String) productMessage.getHeaders().get("source");
        if("Europe".equals(source))
            return "europeRoutedChannel";
        if("United state".equals(source))
            return "usRoutedChannel";

        return "nullChannel";
    }


}
