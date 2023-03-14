package com.hnp.alfrescorestapi.dto;

import java.time.LocalDateTime;

public class Entry {

    private LocalDateTime createdAt;

    private boolean isFolder;

    private boolean isFile;
    private User createdByUser;
    private LocalDateTime modifiedAt;
    private User modifiedByUser;
    private String name;
    private String id;
    private String nodeType;
    private String parentId;

    public Entry(){}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public User getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(User modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "createdAt=" + createdAt +
                ", isFolder=" + isFolder +
                ", isFile=" + isFile +
                ", createdByUser=" + createdByUser +
                ", modifiedAt=" + modifiedAt +
                ", modifiedByUser=" + modifiedByUser +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", nodeType='" + nodeType + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
