package com.github.elover.test;

import com.github.elover.AttrTools;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

public class AttrToolsTest {

    @Test
    public void getWithDefault() {
        String str = AttrTools.getWithDefault(null, "t");
        Assert.assertEquals(str, "t");
        String str2 = AttrTools.getWithDefault("test", "t");
        Assert.assertEquals(str2, "test");
    }

    @Test
    public void testGetWithDefault() {
        User user1 = new User("m", 20);
        User user2 = new User(null, 20);

        String str = AttrTools.getWithDefault(user1, User::getName, "t");
        Assert.assertEquals(str, "m");
        String str2 = AttrTools.getWithDefault(user2, User::getName, "t");
        Assert.assertEquals(str2, "t");

    }

    @Data
    @AllArgsConstructor
    private static class User {
        private String name;
        private Integer age;
    }
}
