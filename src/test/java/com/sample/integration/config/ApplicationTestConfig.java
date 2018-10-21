package com.sample.integration.config;

import com.sample.integration.business.splitter.ProductsSplitter;
import com.sample.integration.configuration.ApplicationConfig;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.sample.integration")
@Import(ApplicationConfig.class)
public class ApplicationTestConfig {

    @Bean
    @Primary
    public ProductsSplitter productsSplitter() {
        ProductsSplitter productsSplitterSpy = Mockito.spy(ProductsSplitter.class);
        return productsSplitterSpy;
    }
}
