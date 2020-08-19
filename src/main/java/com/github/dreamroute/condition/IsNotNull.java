package com.github.dreamroute.condition;

import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;

/**
 * @author w.dehai
 */
public class IsNotNull extends IsNull {
    public IsNotNull(Connector connector, Symbol symbol, String key) {
        super(connector, symbol, key);
    }
}
