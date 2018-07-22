package com.sample.integration.business.transformer;

import com.sample.integration.model.Product;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class EuropeTransformer {

    @Transformer(inputChannel = "routedToEuropeTransformer",outputChannel = "nullChannel")
    public String transformProduct(Message<Product> productMessage) {
        return "Pdf:"+productMessage.getPayload();
    }
}
