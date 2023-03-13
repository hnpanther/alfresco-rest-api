package com.hnp.alfrescorestapi;

import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import com.hnp.alfrescorestapi.utility.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class AlfrescoRestApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlfrescoRestApiApplication.class, args);
    }

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public void run(String... args) throws Exception {


        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();

        try {

            WebClient.ResponseSpec responseSpec = client.get().uri("/nodes/-my-/children")
                    .retrieve();
            Mono<String> listMono = responseSpec.bodyToMono(String.class);
            String block = listMono.block();
            jsonUtil.jsonParserNodeChildren(block);
        } catch (Exception e) {
            System.out.println("alfresco not available");

        }




//        File file = new File("src/main/resources/test.json");
//        ObjectMapper mapper = new ObjectMapper();
//        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        jsonUtil.jsonParserNodeChildren(file);






    }
}
