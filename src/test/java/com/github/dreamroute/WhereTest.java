package com.github.dreamroute;

import com.github.dreamroute.condition.OrderBy;
import com.github.dreamroute.condition.Where;
import org.junit.jupiter.api.Test;

/**
 * where条件测试
 */
public class WhereTest {

    @Test
    public void mm() {
        Where where = new Where();
        where.eq("id", 1L).gt("id", 2L);

        OrderBy orderBy = OrderBy.column("id").desc().column("name").asc();
    }

}
