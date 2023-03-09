package com.hnp.alfrescorestapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;

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

        WebClient.ResponseSpec responseSpec = client.get().uri("/nodes/-my-/children")
                .retrieve();
        Mono<String> listMono = responseSpec.bodyToMono(String.class);
        String block = listMono.block();



        File file = new File("src/main/resources/test.json");
        ObjectMapper mapper = new ObjectMapper();
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        JsonNode jsonNode = new ObjectMapper().readTree(block);
        JsonNode listNode = jsonNode.get("list");
        JsonNode paginationNode = listNode.get("pagination");
        // create pagination...

        JsonNode entries = listNode.get("entries");
        for(int i = 0; i < entries.size(); i++) {
            JsonNode main = entries.get(i);
            JsonNode entryNode = main.get("entry");
            System.out.println(entryNode.get("modifiedAt").textValue());
        }




    }
}
