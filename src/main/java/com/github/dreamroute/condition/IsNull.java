package com.github.dreamroute.condition;

import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;

/**
 * @author w.dehai
 */
public class IsNull extends Condition {
    public IsNull(Connector connector, Symbol symbol, String key) {
        super(connector, symbol, key, null);
    }
}
