package com.micropos.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;

import com.micropos.carts.model.Item;

@Component
public class HttpOutboundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("deliveryChannel")
                .handle(Http.outboundGateway("http://localhost:8090/delivery")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(Item[][].class))
                .get();
    }
}