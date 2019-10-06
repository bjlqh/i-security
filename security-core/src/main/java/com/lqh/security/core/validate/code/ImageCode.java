package com.lqh.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Author: liqihua
 * Date: 2019/10/6 1:42
 * 图片验证码
 * 1.图片，根据随机数生成
 * 2.随机数，放到session里需要验证
 * 3.过期时间,具体到那个日期时间
 */
@Getter
@Setter
@AllArgsConstructor
public class ImageCode {

    private BufferedImage image;
    private String code;
    private LocalDateTime expiredTime;

    public ImageCode(BufferedImage image, String code, long expiredSec) {
        this.image = image;
        this.code = code;
        this.expiredTime = LocalDateTime.now().plusSeconds(expiredSec);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiredTime);
    }
}
