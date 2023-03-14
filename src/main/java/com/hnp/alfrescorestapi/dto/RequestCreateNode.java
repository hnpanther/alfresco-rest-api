package com.hnp.alfrescorestapi.dto;

public class RequestCreateNode {

    private String parentId;
    private String name;

    public RequestCreateNode() {

    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RequestCreateNode{" +
                "parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
