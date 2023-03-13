package com.hnp.alfrescorestapi.cotroller;

import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import com.hnp.alfrescorestapi.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;


    @PostMapping("create-directory/{directoryName}")
    public void createDirectory(@PathVariable String directoryName) {

        this.nodeService.createDirectory("**", directoryName);

    }
}
