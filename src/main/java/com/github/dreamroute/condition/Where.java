package com.github.dreamroute.condition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w.dehai
 */
public class Where {

    private List<Where> wheres = new ArrayList<>();

    public Where eq(String key, Object value) {
        return this;
    }

    public Where gt(String key, Object value) {
        return this;
    }
}
