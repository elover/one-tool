package com.github.elover.test;

import com.github.elover.BeanTools;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BeanToolsTest {

    @Before
    public void init() {

    }

    @Test
    public void copyBean() {

        User1 user1 = new User1("nan", 20);
        User2 user2 = new User2();
        user2 = BeanTools.copyBean(user1, user2);

        Assert.assertEquals(user2.getName(), "nan");
        Assert.assertEquals(user2.getAge(), new Integer(20));
    }

    @Test
    public void copyBeanList() {
        List<User1> user1 = new ArrayList<>();
        user1.add(new User1("nan", 20));

        List<User2> user2 = BeanTools.copyBeanList(user1, User2.class);
        Assert.assertEquals(user2.get(0).getName(), "nan");
        Assert.assertEquals(user2.get(0).getAge(), new Integer(20));
    }

    @Test
    public void copyBeanDeep() {
        User1 user1 = new User1("nan", 20);
        User2 user2 = BeanTools.copyBeanDeep(user1, User2.class);

        Assert.assertEquals(user2.getName(), "nan");
        Assert.assertEquals(user2.getAge(), new Integer(20));
    }

    @Test
    public void copyBeanListDeep() {

        List<User1> user1 = new ArrayList<>();
        user1.add(new User1("nan", 20));

        List<User2> user2 = BeanTools.copyBeanListDeep(user1, User2.class);
        Assert.assertEquals(user2.get(0).getName(), "nan");
        Assert.assertEquals(user2.get(0).getAge(), new Integer(20));

    }

    @Data
    @AllArgsConstructor
    private static class User1 {
        private String name;
        private Integer age;
    }

    @Data
    public static class User2 {
        private String name;
        private Integer age;
    }
}
