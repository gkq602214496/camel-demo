package cn.gkq.camel.test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GKQ
 * @Classname TestReflect
 * @Description TODO
 * @Date 2021/3/26
 */
public class TestReflect {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

        System.out.println(now);
    }

}
