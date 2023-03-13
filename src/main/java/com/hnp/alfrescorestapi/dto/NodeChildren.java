package com.hnp.alfrescorestapi.dto;

import java.util.ArrayList;
import java.util.List;

public class NodeChildren {

    private Pagination pagination;
    private List<Entry> entries = new ArrayList<>();

    public NodeChildren() {

    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
