package com.github.dreamroute.condition;

import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;

import java.util.List;

/**
 * @author w.dehai
 */
public class NotIn extends In {
    public NotIn(Connector connector, Symbol symbol, String key, List<Object> values) {
        super(connector, symbol, key, values);
    }
}
