package com.github.dreamroute.util;

import com.github.dreamroute.domain.User;
import com.github.dreamroute.others.GroupBy;
import com.github.dreamroute.others.Limit;
import com.github.dreamroute.others.OrderBy;
import com.github.dreamroute.others.OrderBy.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class SelectUtilTest {

    private static final List<User> users = new ArrayList<>(3);

    @BeforeAll
    static void initDataTest() {
        User wdh = new User(1L, "w.dehai", "123");
        User dr = new User(2L, "Dreamroute", "456");
        User jd = new User(3L, "Jaedong", null);
        users.add(wdh);
        users.add(dr);
        users.add(jd);
    }

    @Test
    void commonTest() {
        Where where = new Where()
                .andEq("id", 1L)
                .orEq("name", "Jaedong");
        List<User> result = SelectUtil.query(users, where);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void onlyAndTest() {
        List<User> result = SelectUtil.query(users, new Where().andEq("name", "w.dehai"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void onlyOrTest() {
        List<User> result = SelectUtil.query(users, new Where().orEq("name", "w.dehai"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andNotEqTest() {
        List<User> result = SelectUtil.query(users, new Where().andNotEq("name", "w.dehai"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orNotEqTest() {
        List<User> result = SelectUtil.query(users, new Where().orNotEq("name", "w.dehai"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void andBetweenTest() {
        List<User> result = SelectUtil.query(users, new Where().andBetween("id", 2, 3));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orBetweenTest() {
        List<User> result = SelectUtil.query(users, new Where().orBetween("id", 2, 3));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void andLTTest() {
        List<User> result = SelectUtil.query(users, new Where().andLT("id", 2));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orLTTest() {
        List<User> result = SelectUtil.query(users, new Where().orLT("id", 2));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andLTETest() {
        List<User> result = SelectUtil.query(users, new Where().andLTE("id", 2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orLTETest() {
        List<User> result = SelectUtil.query(users, new Where().orLTE("id", 2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void andGTTest() {
        List<User> result = SelectUtil.query(users, new Where().andGT("id", 2));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orGTTest() {
        List<User> result = SelectUtil.query(users, new Where().orGT("id", 2));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andGTETest() {
        List<User> result = SelectUtil.query(users, new Where().andGTE("id", 2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orGTETest() {
        List<User> result = SelectUtil.query(users, new Where().orGTE("id", 2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void andLikeTest() {
        List<User> result = SelectUtil.query(users, new Where().andLike("name", "ae"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orLikeTest() {
        List<User> result = SelectUtil.query(users, new Where().orLike("name", "ae"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andNotLikeTest() {
        List<User> result = SelectUtil.query(users, new Where().andNotLike("name", "ae"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orNototLikeTest() {
        List<User> result = SelectUtil.query(users, new Where().orNotLike("name", "ae"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void andStartWithTest() {
        List<User> result = SelectUtil.query(users, new Where().andStartWith("name", "w.d"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orStartWithTest() {
        List<User> result = SelectUtil.query(users, new Where().orStartWith("name", "w.d"));
        Assertions.assertEquals(1, result.size());
    }


    @Test
    void andEndWithTest() {
        List<User> result = SelectUtil.query(users, new Where().andEndWith("name", "dong"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orEndWithTest() {
        List<User> result = SelectUtil.query(users, new Where().orEndWith("name", "dong"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andOrInTest() {
        List<User> result = SelectUtil.query(users, new Where().andIn("name", Arrays.asList("w.dehai", "Jaedong")));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orOrInTest() {
        List<User> result = SelectUtil.query(users, new Where().orIn("name", Arrays.asList("w.dehai", "Jaedong")));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void andNotInTest() {
        List<User> result = SelectUtil.query(users, new Where().andNotIn("name", Arrays.asList("w.dehai", "Jaedong")));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orNotInTest() {
        List<User> result = SelectUtil.query(users, new Where().orNotIn("name", Arrays.asList("w.dehai", "Jaedong")));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andIsNullTest() {
        List<User> result = SelectUtil.query(users, new Where().andIsNull("password"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void orIsNullTest() {
        List<User> result = SelectUtil.query(users, new Where().orIsNull("password"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void andIsNotNullTest() {
        List<User> result = SelectUtil.query(users, new Where().andIsNotNull("password"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orIsNotNullTest() {
        List<User> result = SelectUtil.query(users, new Where().orIsNotNull("password"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void orderByTest() {
        List<User> result = SelectUtil.query(users, null, new OrderBy().column("id", Order.DESC), null);
        Long[] ids = result.stream().map(User::getId).toArray(Long[]::new);
        Assertions.assertArrayEquals(new Long[]{3L, 2L, 1L}, ids);
    }

    @Test
    void limitTest() {
        List<User> result = SelectUtil.query(users, null, null, new Limit(2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void groupByTest() {
        Map<Long, List<User>> map = SelectUtil.query(users, null, null, null, new GroupBy("id"));
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        map.forEach((k, v) -> {
            Assertions.assertEquals(true, ids.contains(1L));
            Assertions.assertEquals(true, ids.contains(2L));
            Assertions.assertEquals(true, ids.contains(3L));
        });
    }

    @Test
    void allParamTest() {
        Map<Long, List<User>> result = SelectUtil.query(users, new Where().andEq("id", 1L), new OrderBy().column("id"), new Limit(2), new GroupBy("id"));
        Assertions.assertEquals(1L, result.get(1L).get(0).getId());
    }

}
