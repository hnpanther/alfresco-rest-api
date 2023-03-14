package com.hnp.alfrescorestapi.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnp.alfrescorestapi.dto.Entry;
import com.hnp.alfrescorestapi.dto.NodeChildren;
import com.hnp.alfrescorestapi.dto.Pagination;
import com.hnp.alfrescorestapi.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonUtil {


    private final Logger logger = LoggerFactory.getLogger(JsonUtil.class);


    public NodeChildren jsonParserNodeChildren(String inputJson) throws JsonProcessingException {


        JsonNode jsonNode = new ObjectMapper().readTree(inputJson);
        JsonNode listNode = jsonNode.get("list");
        JsonNode paginationNode = listNode.get("pagination");
        Pagination pagination = new Pagination();
        pagination.setCount(Integer.parseInt(paginationNode.get("count").toString()));
        pagination.setMaxItems(Integer.parseInt(paginationNode.get("maxItems").toString()));
        pagination.setSkipCount(Integer.parseInt(paginationNode.get("skipCount").toString()));
        pagination.setTotalItems(Integer.parseInt(paginationNode.get("totalItems").toString()));
        pagination.setHasMoreItems(Boolean.parseBoolean(paginationNode.get("hasMoreItems").toString()));



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        List<Entry> entries = new ArrayList<>();
        JsonNode entriesNode = listNode.get("entries");
        for(int i = 0; i < entriesNode.size(); i++) {
            JsonNode main = entriesNode.get(i);
            JsonNode entryNode = main.get("entry");
            Entry entry = new Entry();

            entry.setModifiedAt(LocalDateTime.parse(entryNode.get("modifiedAt").textValue(), formatter));
            entry.setCreatedAt(LocalDateTime.parse(entryNode.get("createdAt").textValue(), formatter));
            entry.setFolder(Boolean.parseBoolean(entryNode.get("isFolder").toString()));
            entry.setFile(Boolean.parseBoolean(entryNode.get("isFile").toString()));
            entry.setName(entryNode.get("name").textValue());
            entry.setId(entryNode.get("id").textValue());
            entry.setNodeType(entryNode.get("nodeType").textValue());
            entry.setParentId(entryNode.get("parentId").textValue());

            JsonNode createdUserNode = entryNode.get("createdByUser");
            JsonNode modifiedUserNode = entryNode.get("modifiedByUser");

            User createdUser = new User();
            createdUser.setId(createdUserNode.get("id").textValue());
            createdUser.setDisplayName(createdUserNode.get("displayName").textValue());

            User modifiedUser = new User();
            modifiedUser.setId(modifiedUserNode.get("id").textValue());
            modifiedUser.setDisplayName(modifiedUserNode.get("displayName").textValue());

            entry.setCreatedByUser(createdUser);
            entry.setModifiedByUser(modifiedUser);

            entries.add(entry);
        }

        NodeChildren nodeChildren = new NodeChildren();
        nodeChildren.setPagination(pagination);
        nodeChildren.setEntries(entries);

        return nodeChildren;
    }
}
