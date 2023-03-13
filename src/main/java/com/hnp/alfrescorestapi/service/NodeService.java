package com.hnp.alfrescorestapi.service;

import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NodeService {

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    public String createDirectory(String path, String directoryName) {
        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();

        try {

            WebClient.ResponseSpec responseSpec = client.get().uri(
                            builder -> builder.path("/queries/nodes")
                                    .queryParam("term", directoryName).queryParam("rootNodeId", "cf6ec19f-b520-4c79-88a5-55de9bad1c9c")
                                    .build()
                    )
                    .retrieve();
            Mono<String> listMono = responseSpec.bodyToMono(String.class);

            String s = listMono.block();
            System.out.println(s);

//            listMono.subscribe(output -> {
//                System.out.println(output);
//            });

        } catch (Exception e) {
            System.out.println("alfresco not available");
            e.printStackTrace();
        }
        return "ok";
    }
}
