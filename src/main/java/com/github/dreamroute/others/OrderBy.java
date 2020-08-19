package com.github.dreamroute.others;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w.dehai
 */
@Data
public class OrderBy {

    List<OrderInfo> orderInfos = new ArrayList<>();

    public OrderBy column(String column) {
        return this.column(column, Order.ASC);
    }

    public OrderBy column(String column, Order order) {
        orderInfos.add(new OrderInfo(column, order));
        return this;
    }

    public static enum Order {
        ASC,
        DESC;
    }

    @Data
    @AllArgsConstructor
    public static class OrderInfo {
        private String column;
        private Order order;
    }


}
