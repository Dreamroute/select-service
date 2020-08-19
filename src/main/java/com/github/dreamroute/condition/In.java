package com.github.dreamroute.condition;

import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;
import lombok.Data;

import java.util.List;

/**
 * @author w.dehai
 */
@Data
public class In extends Condition {

    private List<Object> values;

    public In(Connector connector, Symbol symbol, String key, List<Object> values) {
        super(connector, symbol, key, null);
        this.values = values;
    }
}
