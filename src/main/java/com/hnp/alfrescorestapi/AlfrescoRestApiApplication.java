package com.hnp.alfrescorestapi;

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

    @Override
    public void run(String... args) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1")
                .defaultHeaders(header -> header.setBasicAuth("admin", "admin"))
                .build();

        WebClient.ResponseSpec s = client.get().uri("/nodes/-my-/children")
                .retrieve();
        Flux<String> out = s.bodyToFlux(String.class);

        System.out.println(out.blockFirst());
    }
}
