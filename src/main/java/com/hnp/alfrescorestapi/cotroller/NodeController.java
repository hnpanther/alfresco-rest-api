package com.hnp.alfrescorestapi.cotroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import com.hnp.alfrescorestapi.dto.NodeChildren;
import com.hnp.alfrescorestapi.dto.NodeSearch;
import com.hnp.alfrescorestapi.dto.RequestCreateNode;
import com.hnp.alfrescorestapi.exception.CustomForbiddenException;
import com.hnp.alfrescorestapi.service.NodeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    private final Logger logger = LoggerFactory.getLogger(NodeController.class);


    @PostMapping("node/directory")
    public ResponseEntity<String> createDirectory(@RequestBody RequestCreateNode requestCreateNode) {
        logger.info("call node/directory");
        try {

            String created = this.nodeService.createDirectory(requestCreateNode);
            if(created == null) {
                return ResponseEntity.status(409).build();
            }
            return ResponseEntity.ok().body(created);
        } catch (WebClientResponseException.NotFound | WebClientResponseException.Forbidden | JsonProcessingException e) {
            if(e instanceof WebClientResponseException.Forbidden) {
                throw new CustomForbiddenException();
            }
            return ResponseEntity.badRequest().build();
        }

    }


    @GetMapping("/node/list/{rootNodeId}")
    public ResponseEntity<NodeChildren> getNodeList(@PathVariable String rootNodeId) {
        logger.info("call /node/list/{rootNodeId}");
        try {
            NodeChildren nodeChildren = this.nodeService.getNodeList(rootNodeId);
            return ResponseEntity.ok().body(nodeChildren);
        } catch (WebClientResponseException.NotFound | WebClientResponseException.Forbidden | JsonProcessingException e) {

            if(e instanceof WebClientResponseException.Forbidden) {
                throw new CustomForbiddenException();
            }

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
        logger.info("call /node/checkExists");
        try {
            boolean check = this.nodeService.checkNodeExists(nodeSearch);
            if(check) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (JsonProcessingException | WebClientResponseException.Forbidden e) {

            if(e instanceof WebClientResponseException.Forbidden) {
                throw new CustomForbiddenException();
            }

            logger.warn("can not parsing response with rootNodeId=" + nodeSearch.getParentId(), e);
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("node/file")
    public ResponseEntity<String> uploadFile(@RequestPart("filedata") MultipartFile file, @RequestParam("parentId") String parentId) {
        logger.info("call node/file");
        try {
            String created = this.nodeService.uploadFile(file, parentId, file.getOriginalFilename());
            if(created == null) {
                return ResponseEntity.status(409).build();
            }
            return ResponseEntity.ok().body(created);
        } catch (IOException | WebClientResponseException.Forbidden e) {

            if(e instanceof WebClientResponseException.Forbidden) {
                throw new CustomForbiddenException();
            }
            logger.error("exception handler: " +e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping("node/{fileName}/{nodeId}")
    public Mono<ResponseEntity<byte[]>> donwloadFile(@PathVariable String nodeId, @PathVariable String fileName) {
        logger.info("call node/{fileName}/{nodeId}");
        try {
            return this.nodeService.downloadFile(nodeId, fileName);
        } catch (Exception e) {

            if(e instanceof WebClientResponseException.Forbidden) {
                throw new CustomForbiddenException();
            }
            return Mono.just(ResponseEntity.status(404)
                    .body(null));

        }


    }


}
