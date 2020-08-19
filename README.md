# select-service
> 模拟SQL查询

# 环境：
- JDK8+
- IDE需要支持Lombok

# 用法
- 分组(由于带有分组，返回值为Map类型)
```
<T, E> Map<E, List<T>> SelectUtilTest.query(List<T> data, Where where, OrderBy orderBy, Limit limit, GroupBy groupBy);
```

- 非分组
```
<T> List<T> query(List<T> data, Where where)
<T> List<T> query(List<T> data, Where where, OrderBy orderBy, Limit limit)
```

> 说明：所有参数均可为空