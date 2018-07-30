package com.scservice.order.utils;

import java.util.Random;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:20 2018/7/30
 * @Description:
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
