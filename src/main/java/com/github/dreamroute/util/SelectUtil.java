package com.github.dreamroute.util;

import com.github.dreamroute.condition.Between;
import com.github.dreamroute.condition.Condition;
import com.github.dreamroute.condition.In;
import com.github.dreamroute.enums.Connector;
import com.github.dreamroute.enums.Symbol;
import com.github.dreamroute.others.OrderBy;
import com.github.dreamroute.others.OrderBy.OrderInfo;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author w.dehai
 */
public class SelectUtil {

    private SelectUtil() {}

    public static <T> List<T> query(List<T> data, Where where) {
        return query(data, where, null);
    }

    public static <T> List<T> query(List<T> data, Where where, OrderBy orderBy) {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }

        List<OrderInfo> orderInfos = orderBy.getOrderInfos();
        if (orderInfos != null && !orderInfos.isEmpty()) {
            for (OrderInfo orderInfo : orderInfos) {
                data.sort((o1, o2) -> {
                    Reflector r = new Reflector(o1.getClass());
                    return 0;
                });
            }
        }


        return data.stream()
                .filter(e -> executeConditions(e, where))
                .collect(Collectors.toList());
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
