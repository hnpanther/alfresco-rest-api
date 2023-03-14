package com.hnp.alfrescorestapi.cotroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import com.hnp.alfrescorestapi.dto.NodeChildren;
import com.hnp.alfrescorestapi.dto.NodeSearch;
import com.hnp.alfrescorestapi.dto.RequestCreateNode;
import com.hnp.alfrescorestapi.service.NodeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    private final Logger logger = LoggerFactory.getLogger(NodeController.class);


    @PostMapping("create-directory")
    public void createDirectory(@RequestBody RequestCreateNode requestCreateNode) {

        try {
            this.nodeService.createDirectory(requestCreateNode);
        } catch (WebClientResponseException.NotFound | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    @GetMapping("/node/list/{rootNodeId}")
    public ResponseEntity<NodeChildren> getNodeList(@PathVariable String rootNodeId) {

        try {
            NodeChildren nodeChildren = this.nodeService.getNodeList(rootNodeId);
            return ResponseEntity.ok().body(nodeChildren);
        } catch (WebClientResponseException.NotFound | JsonProcessingException e) {

            if(e instanceof WebClientResponseException.NotFound) {
                logger.info("rootNodeId Not Found, rootNodeId=" + rootNodeId, e);
                return ResponseEntity.notFound().build();
            }

            logger.warn("can not parsing response with rootNodeId=" + rootNodeId, e);
            return ResponseEntity.badRequest().build();

        }


    }

    @GetMapping("/node/checkExists")
    public ResponseEntity checkNodeExists(@RequestBody @Valid NodeSearch nodeSearch) {
        try {
            boolean check = this.nodeService.checkNodeExists(nodeSearch);
            if(check) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (JsonProcessingException e) {
            logger.warn("can not parsing response with rootNodeId=" + nodeSearch.getParentId(), e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/test")
    public void test() {
        this.nodeService.test("1");
    }


}
