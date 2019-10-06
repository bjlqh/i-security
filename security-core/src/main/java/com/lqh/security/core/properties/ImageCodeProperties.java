package com.lqh.security.core.properties;

import lombok.Data;

/**
 * Author: liqihua
 * Date: 2019/10/6 17:38
 */
@Data
public class ImageCodeProperties {
    /**
     * 图片宽
     */
    private int width = 95;

    /**
     * 图片高
     */
    private int height = 25;

    /**
     * 随机产生字符数量
     */
    private int length = 4;

    /**
     * 过期时间
     */
    private long expireSec = 60L;

    /**
     * 指定url数组
     */
    private String urls;
}
