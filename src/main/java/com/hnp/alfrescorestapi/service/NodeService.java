package com.hnp.alfrescorestapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import com.hnp.alfrescorestapi.dto.*;
import com.hnp.alfrescorestapi.utility.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class NodeService {

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    @Autowired
    private JsonUtil jsonUtil;

    private final Logger logger = LoggerFactory.getLogger(NodeService.class);

    public String createDirectory(RequestCreateNode requestCreateNode) throws  JsonProcessingException {

        System.out.println(requestCreateNode);
        NodeSearch nodeSearch = new NodeSearch();
        nodeSearch.setParentId(requestCreateNode.getParentId());
        nodeSearch.setType("cm:folder");
        nodeSearch.setTitle(requestCreateNode.getName());
        boolean check = checkNodeExists(nodeSearch);

        if(check) {
            return null;
        }

        CreateNode createNode = new CreateNode();
        createNode.setNodeType("cm:folder");
        createNode.setName(requestCreateNode.getName());

        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();

        WebClient.ResponseSpec responseSpec = client.post().uri(
                        builder -> builder.path("/nodes/" + requestCreateNode.getParentId() + "/children").build()
                ).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createNode)
                .retrieve();

        Mono<String> stringMono = responseSpec.bodyToMono(String.class);
        System.out.println(stringMono.block());
        //add WebClientResponseException.Conflict ...

        return "ok";
    }



    public NodeChildren getNodeList(String rootNodeId) throws WebClientResponseException.NotFound, JsonProcessingException {
        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();

        NodeChildren nodeChildren = null;

        WebClient.ResponseSpec responseSpec = client.get().uri("/nodes/" + rootNodeId + "/children")
                .retrieve();

        Mono<String> listMono = responseSpec.bodyToMono(String.class);

        String responseBody = listMono.block();
        nodeChildren = jsonUtil.jsonParserNodeChildren(responseBody);

        return nodeChildren;
    }

    public boolean checkNodeExists(NodeSearch nodeSearch) throws JsonProcessingException {
        WebClient client = WebClient.builder()
                .baseUrl(alfrescoConfiguration.getApiUrl())
                .defaultHeaders(header ->
                        header.setBasicAuth(alfrescoConfiguration.getApiUsername(), alfrescoConfiguration.getApiPassword()))
                .build();
        System.out.println(nodeSearch);
        WebClient.ResponseSpec responseSpec = client.get().uri(
                        builder -> builder.path("/queries/nodes")
                                .queryParam("term", nodeSearch.getTitle())
                                .queryParam("rootNodeId",nodeSearch.getParentId())
                                .queryParam("nodeType", nodeSearch.getType())
                                .build()
                )
                .retrieve();
        try {
            Mono<String> listMono = responseSpec.bodyToMono(String.class);
            String response = listMono.block();
            NodeChildren nodeChildren = jsonUtil.jsonParserNodeChildren(response);
            for(Entry entry: nodeChildren.getEntries()) {
                if(entry.getParentId().equals(nodeSearch.getParentId()) && entry.getName().equals(nodeSearch.getTitle())) {
                    return true;
                }
            }
            return false;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }

    }


    public void test(String rootNodeId) {

    }

}
