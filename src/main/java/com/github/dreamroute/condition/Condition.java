package com.github.dreamroute.condition;

import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;
import lombok.Data;

/**
 * @author w.dehai
 */
@Data
public class Condition {
    private Connector connector;
    private Symbol symbol;
    private String key;
    private Object value;

    public Condition(Connector connector, Symbol symbol, String key, Object value) {
        this.connector = connector;
        this.symbol = symbol;
        this.key = key;
        this.value = value;
    }

}
