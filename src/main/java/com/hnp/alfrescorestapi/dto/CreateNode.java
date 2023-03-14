package com.hnp.alfrescorestapi.dto;

public class CreateNode {

    private String name;

    private String nodeType;

    public CreateNode() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "CreateNode{" +
                "name='" + name + '\'' +
                ", nodeType='" + nodeType + '\'' +
                '}';
    }
}
