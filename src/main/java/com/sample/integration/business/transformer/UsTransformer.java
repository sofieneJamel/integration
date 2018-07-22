package com.sample.integration.business.transformer;

import com.sample.integration.model.Product;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class UsTransformer {

    @Transformer(inputChannel = "routedToUsTransformer",outputChannel = "nullChannel")
    public String transformProduct(Message<Product> productMessage) {
        return "Csv:"+productMessage.getPayload();
    }
}
