package cn.gkq.camel.annotation;

import java.lang.annotation.*;

/**
 * @author GKQ
 * @Classname Vip
 * @Description TODO
 * @Date 2021/3/5
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
@Documented
public @interface Vip {

    int value();
}
