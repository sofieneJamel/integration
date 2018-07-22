package com.sample.integration.business.router;

import com.sample.integration.model.ProductsWrapper;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ProductsRouter {


    @Router(inputChannel = "productsRoutingChannel")
    public String router(Message<ProductsWrapper> productsMessage) {
        String source = (String) productsMessage.getHeaders().get("source");

        if("Europe".equals(source))
            return "routedToEuropeTransformer";
        if("United state".equals(source))
            return "routedToUsTransformer";

        return "nullChannel";
    }


}
