package com.github.dreamroute.util;

import com.github.dreamroute.condition.Between;
import com.github.dreamroute.condition.Condition;
import com.github.dreamroute.condition.In;
import com.github.dreamroute.condition.IsNotNull;
import com.github.dreamroute.condition.IsNull;
import com.github.dreamroute.condition.NotIn;
import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w.dehai
 */
public class Where {

    private final List<Condition> conditions = new ArrayList<>();

    // -- EQ
    public  Where andEq(String key, Object value) {
        return this.eq(Connector.AND, key, value);
    }

    public  Where orEq(String key, Object value) {
        return this.eq(Connector.OR, key, value);
    }

    private Where eq(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.EQ, key, value));
        return this;
    }

    // -- NotEQ
    public Where andNotEq(String key, Object value) {
        return this.notEq(Connector.AND, key, value);
    }

    public Where orNotEq(String key, Object value) {
        return this.notEq(Connector.OR, key, value);
    }

    private Where notEq(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.NotEQ, key, value));
        return this;
    }

    // -- between
    public Where andBetween(String key, Number start, Number end) {
        return this.between(Connector.AND, key, start, end);
    }

    public Where orBetween(String key, Number start, Number end) {
        return this.between(Connector.OR, key, start, end);
    }

    private Where between(Connector connector, String key, Number start, Number end) {
        conditions.add(new Between(connector, Symbol.Between, key, start, end));
        return this;
    }

    // -- lt
    public Where andLT(String key, Object value) {
        return this.lt(Connector.AND, key, value);
    }

    public Where orLT(String key, Object value) {
        return this.lt(Connector.OR, key, value);
    }

    private Where lt(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.LT, key, value));
        return this;
    }

    // -- lte
    public Where andLTE(String key, Object value) {
        return this.lte(Connector.AND, key, value);
    }

    public Where orLTE(String key, Object value) {
        return this.lte(Connector.OR, key, value);
    }

    private Where lte(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.LTE, key, value));
        return this;
    }

    // -- gt
    public Where andGT(String key, Object value) {
        return this.gt(Connector.AND, key, value);
    }

    public Where orGT(String key, Object value) {
        return this.gt(Connector.OR, key, value);
    }

    private Where gt(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.GT, key, value));
        return this;
    }

    // -- gte
    public Where andGTE(String key, Object value) {
        return this.gte(Connector.AND, key, value);
    }

    public Where orGTE(String key, Object value) {
        return this.gte(Connector.OR, key, value);
    }

    private Where gte(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.GTE, key, value));
        return this;
    }

    // -- like
    public Where andLike(String key, String value) {
        return this.like(Connector.AND, key, value);
    }

    public Where orLike(String key, String value) {
        return this.like(Connector.OR, key, value);
    }

    private Where like(Connector connector, String key, Object value) {
        conditions.add(new Condition(connector, Symbol.Like, key, value));
        return this;
    }

    // -- not like
    public Where andNotLike(String key, String value) {
        return this.notLike(Connector.AND, key, value);
    }

    public Where orNotLike(String key, String value) {
        return this.notLike(Connector.OR, key, value);
    }

    private Where notLike(Connector connector, String key, String value) {
        conditions.add(new Condition(connector, Symbol.NotLike, key, value));
        return this;
    }

    // -- startWith
    public Where andStartWith(String key, String value) {
        return this.startWith(Connector.AND, key, value);
    }

    public Where orStartWith(String key, String value) {
        return this.startWith(Connector.OR, key, value);
    }

    private Where startWith(Connector connector, String key, String value) {
        conditions.add(new Condition(connector, Symbol.StartWith, key, value));
        return this;
    }

    // -- endWith
    public Where andEndWith(String key, String value) {
        return this.endWith(Connector.AND, key, value);
    }

    public Where orEndWith(String key, String value) {
        return this.endWith(Connector.OR, key, value);
    }

    private Where endWith(Connector connector, String key, String value) {
        conditions.add(new Condition(connector, Symbol.EndWith, key, value));
        return this;
    }

    // -- in
    public Where andIn(String key, List<Object> values) {
        return this.in(Connector.AND, key, values);
    }

    public Where orIn(String key, List<Object> values) {
        return this.in(Connector.OR, key, values);
    }

    private Where in(Connector connector, String key, List<Object> values) {
        conditions.add(new In(connector, Symbol.In, key, values));
        return this;
    }

    // -- not in
    public Where andNotIn(String key, List<Object> values) {
        return this.notIn(Connector.AND, key, values);
    }

    public Where orNotIn(String key, List<Object> values) {
        return this.notIn(Connector.OR, key, values);
    }

    private Where notIn(Connector connector, String key, List<Object> values) {
        conditions.add(new NotIn(connector, Symbol.NotIn, key, values));
        return this;
    }

    // -- is null
    public Where andIsNull(String key) {
        return this.isNull(Connector.AND, key);
    }

    public Where orIsNull(String key) {
        return this.isNull(Connector.OR, key);
    }

    private Where isNull(Connector connector, String key) {
        conditions.add(new IsNull(connector, Symbol.IsNull, key));
        return this;
    }

    // -- is not null
    public Where andIsNotNull(String key) {
        return this.isNotNull(Connector.AND, key);
    }

    public Where orIsNotNull(String key) {
        return this.isNotNull(Connector.OR, key);
    }

    private Where isNotNull(Connector connector, String key) {
        conditions.add(new IsNotNull(connector, Symbol.IsNotNull, key));
        return this;
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

}
