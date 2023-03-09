package com.hnp.alfrescorestapi;

import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class AlfrescoRestApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlfrescoRestApiApplication.class, args);
    }

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    @Override
    public void run(String... args) throws Exception {


        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();

        WebClient.ResponseSpec s = client.get().uri("/nodes/-my-/children")
                .retrieve();
        Flux<String> out = s.bodyToFlux(String.class);

        System.out.println(out.blockFirst());
    }
}
