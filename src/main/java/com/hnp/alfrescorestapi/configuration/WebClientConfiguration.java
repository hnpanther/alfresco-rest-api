package com.hnp.alfrescorestapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    public WebClientConfiguration() {}

    @Bean
    public WebClient createBasicWebclient() {
        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();
        return client;
    }
}
