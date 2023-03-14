package com.hnp.alfrescorestapi.dto;

import com.hnp.alfrescorestapi.validator.NodeTypeConstraint;
import jakarta.validation.constraints.NotBlank;

public class NodeSearch {

    private String id;

    @NotBlank(message = "parentId is blank")
    private String parentId;
    @NotBlank(message = "title is blank")
    private String title;
    @NodeTypeConstraint
    private String type;
    private String path;

    public NodeSearch() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "NodeSearch{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
