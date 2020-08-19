package com.github.dreamroute.util;

import com.github.dreamroute.condition.Between;
import com.github.dreamroute.condition.Condition;
import com.github.dreamroute.condition.In;
import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;
import com.github.dreamroute.others.GroupBy;
import com.github.dreamroute.others.Limit;
import com.github.dreamroute.others.OrderBy;
import com.github.dreamroute.others.OrderBy.Order;
import com.github.dreamroute.others.OrderBy.OrderInfo;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author w.dehai
 */
public class SelectUtil {

    private SelectUtil() {}

    public static <T> List<T> query(List<T> data, Where where) {
        return query(data, where, null, null);
    }

    public static <T, E> Map<E, List<T>> query(List<T> data, Where where, OrderBy orderBy, Limit limit, GroupBy groupBy) {
        List<T> dataList = query(data, where, orderBy, limit);
        Map<E, List<T>> result = new HashMap<>();
        if (groupBy != null) {
            result = dataList.stream().collect(Collectors.groupingBy(e -> {
                Reflector reflector = new Reflector(e.getClass());
                try {
                    return (E) reflector.getGetInvoker(groupBy.getProp()).invoke(e, null);
                } catch (Exception ee) {
                    throw new SelectServiceException();
                }
            }));
        }
        return result;
    }

    public static <T> List<T> query(List<T> data, Where where, OrderBy orderBy, Limit limit) {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }

        Stream<T> stream = data.stream()
                .filter(e -> executeConditions(e, where));

        Comparator comparator = getComparator(orderBy);
        if (comparator != null) {
            stream = stream.sorted(comparator);
        }

        if (limit != null && limit.getSize() != null) {
            if (limit.getSize() < 0) {
                throw new SelectServiceException("limit不能小于0");
            }
            stream = stream.limit(limit.getSize());
        }
        return stream.collect(Collectors.toList());
    }

    private static <T> Comparator<T> getComparator(OrderBy orderBy) {
        Comparator<T> c = null;
        if (orderBy != null) {
            List<OrderInfo> orderInfos = orderBy.getOrderInfos();
            if (orderInfos != null && !orderInfos.isEmpty()) {
                for (OrderInfo orderInfo : orderInfos) {
                    Comparator cp = Comparator.comparing(e -> {
                        Reflector r = new Reflector(e.getClass());
                        try {
                            return (Comparable) r.getGetInvoker(orderInfo.getColumn()).invoke(e, null);
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        return null;
                    });
                    if (orderInfo.getOrder() == Order.DESC) {
                        cp = cp.reversed();
                    }
                    c = c == null ? cp : c.thenComparing(cp);
                }
            }
        }
        return c;
    }

    public static <T> boolean executeConditions(T e, Where where) {
        if (where == null) {
            return true;
        }
        List<Condition> conditions = where.getConditions();
        Reflector reflector = new Reflector(e.getClass());
        Boolean result = null;
        if (conditions != null && !conditions.isEmpty()) {
            for (int i = 0; i < conditions.size(); i++) {
                Condition condition = conditions.get(i);
                Invoker getInvoker = reflector.getGetInvoker(condition.getKey());
                try {
                    Object dataValue = getInvoker.invoke(e, null);
                    boolean synbolResult = getResultBySymbol(condition.getSymbol(), dataValue, condition);
                    Connector conn = condition.getConnector();
                    if (conn == Connector.AND) {
                        result = result == null ? synbolResult : result && synbolResult;
                    } else if (conn == Connector.OR) {
                        result = result == null ? synbolResult : result || synbolResult;
                    }
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }
            }
        }
        return result;
    }

    private static boolean getResultBySymbol(Symbol symbol, Object dataValue, Condition condition) {
        boolean result;

        switch (symbol) {
            case EQ:
                result = Objects.equals(dataValue, condition.getValue());
                break;
            case NotEQ:
                result = !Objects.equals(dataValue, condition.getValue());
                break;
            case Between: {
                Between between = (Between) condition;
                double start = between.getStart().doubleValue();
                double end = between.getEnd().doubleValue();
                double dv = ((Number) dataValue).doubleValue();
                result = dv >= start && dv <= end;
            }
            ;
            break;
            case LT:
                result = ((Number) dataValue).doubleValue() < ((Number) condition.getValue()).doubleValue();
                break;
            case LTE:
                result = ((Number) dataValue).doubleValue() <= ((Number) condition.getValue()).doubleValue();
                break;
            case GT:
                result = ((Number) dataValue).doubleValue() > ((Number) condition.getValue()).doubleValue();
                break;
            case GTE:
                result = ((Number) dataValue).doubleValue() >= ((Number) condition.getValue()).doubleValue();
                break;
            case Like:
                result = ((String) dataValue).indexOf((String) condition.getValue()) != -1;
                break;
            case NotLike:
                result = ((String) dataValue).indexOf((String) condition.getValue()) == -1;
                break;
            case StartWith:
                result = ((String) dataValue).startsWith((String) condition.getValue());
                break;
            case EndWith:
                result = ((String) dataValue).endsWith((String) condition.getValue());
                break;
            case In: {
                In in = (In) condition;
                List<Object> values = in.getValues();
                result = values.contains(dataValue);
            }
            ;
            break;
            case NotIn: {
                In in = (In) condition;
                List<Object> values = in.getValues();
                result = !values.contains(dataValue);
            }
            ;
            break;
            case IsNull:
                result = dataValue == null;
                break;
            case IsNotNull:
                result = dataValue != null;
                break;
            default:
                result = true;
        }
        return result;
    }

}
