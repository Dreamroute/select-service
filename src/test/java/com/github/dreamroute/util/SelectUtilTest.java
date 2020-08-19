package com.github.dreamroute.util;

import com.github.dreamroute.domain.User;
import com.github.dreamroute.others.OrderBy;
import com.github.dreamroute.others.OrderBy.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectUtilTest {

    private static List<User> users = new ArrayList<>(3);

    @BeforeAll
    public static void initDataTest() {
        User wdh = new User(1L, "w.dehai", "123");
        User dr = new User(2L, "Dreamroute", "456");
        User jd = new User(3L, "Jaedong", null);
        users.add(wdh);
        users.add(dr);
        users.add(jd);
    }

    @Test
    public void commonTest() {
        Where where = new Where()
                .andEq("id", 1L)
                .orEq("name", "Jaedong");
        List<User> result = SelectUtil.query(users, where);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void onlyAndTest() {
        List<User> result = SelectUtil.query(users, new Where().andEq("name", "w.dehai"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void onlyOrTest() {
        List<User> result = SelectUtil.query(users, new Where().orEq("name", "w.dehai"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void betweenTest() {
        List<User> result = SelectUtil.query(users, new Where().andBetween("id", 2, 3));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void ltTest() {
        List<User> result = SelectUtil.query(users, new Where().andLT("id", 2));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void lteTest() {
        List<User> result = SelectUtil.query(users, new Where().andLTE("id", 2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void gtTest() {
        List<User> result = SelectUtil.query(users, new Where().andGT("id", 2));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void gteTest() {
        List<User> result = SelectUtil.query(users, new Where().andGTE("id", 2));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void likeTest() {
        List<User> result = SelectUtil.query(users, new Where().andLike("name", "ae"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void notLikeTest() {
        List<User> result = SelectUtil.query(users, new Where().andNotLike("name", "ae"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void startWithTest() {
        List<User> result = SelectUtil.query(users, new Where().andStartWith("name", "w.d"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void endWithTest() {
        List<User> result = SelectUtil.query(users, new Where().andEndWith("name", "dong"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void inTest() {
        List<User> result = SelectUtil.query(users, new Where().andIn("name", Arrays.asList("w.dehai", "Jaedong")));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void notInTest() {
        List<User> result = SelectUtil.query(users, new Where().andNotIn("name", Arrays.asList("w.dehai", "Jaedong")));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void isNullTest() {
        List<User> result = SelectUtil.query(users, new Where().andIsNull("password"));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void isNotNullTest() {
        List<User> result = SelectUtil.query(users, new Where().andIsNotNull("password"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void orderByTest() {
        List<User> result = SelectUtil.query(users, null, new OrderBy().column("id").column("name", Order.DESC));
        System.err.println(result);
    }

}
