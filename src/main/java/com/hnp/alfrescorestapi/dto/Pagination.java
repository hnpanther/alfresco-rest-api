package com.hnp.alfrescorestapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Pagination {

    private int count;
    private boolean hasMoreItems;
    private int totalItems;
    private int skipCount;
    private int maxItems;


    public Pagination(){}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHasMoreItems() {
        return hasMoreItems;
    }

    public void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "count=" + count +
                ", hasMoreItems=" + hasMoreItems +
                ", totalItems=" + totalItems +
                ", skipCount=" + skipCount +
                ", maxItems=" + maxItems +
                '}';
    }
}
