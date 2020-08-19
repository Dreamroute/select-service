package com.github.dreamroute.others;

import lombok.Data;

/**
 * w.dehai
 */
public class Limit {

    private Integer size;

    public Limit(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return this.size;
    }

}
