package com.github.dreamroute.condition;

import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;
import lombok.Data;

/**
 * @author w.dehai
 */
@Data
public class Between extends Condition {

    private Number start;
    private Number end;

    public Between(Connector connector, Symbol symbol, String key, Number start, Number end) {
        super(connector, symbol, key, null);
        this.start = start;
        this.end = end;
    }

}
