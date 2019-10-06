package com.lqh.security.core.validate.service.impl;

import com.lqh.security.core.properties.SecurityProperties;
import com.lqh.security.core.validate.code.ImageCode;
import com.lqh.security.core.validate.service.IValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Author: liqihua
 * Date: 2019/10/6 23:49
 */

@Slf4j
public class ImageCodeGenerator implements IValidateCodeGenerator {

    /**
     * 随机产生数字与字母组合的字符串
     */
    private static final String RAND_STRING = "0123456789abcdefghijklmnopqrstuvwxyz";


    private Random random = new Random();

    private SecurityProperties securityProperties;

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 生成随机图片
     */
    @Override
    public ImageCode getRandCode(HttpServletRequest request) {
        //高度和宽度能取到从请求中获取，取不到就从配置中获取
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidateCode().getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getValidateCode().getImageCode().getHeight());

        int length = securityProperties.getValidateCode().getImageCode().getLength();
        long expireSec = securityProperties.getValidateCode().getImageCode().getExpireSec();

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        //图片大小
        g.fillRect(0, 0, width, height);
        //字体大小
        g.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        //字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= 100; i++) {
            drawLine(g, width, height);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= length; i++) {
            randomString = drawString(g, randomString, i);
        }
        log.info(randomString);

        g.dispose();

        return new ImageCode(image, randomString, expireSec);
    }

    /**
     * 绘制字符串
     */
    private String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(RAND_STRING
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g, int width, int height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private String getRandomString(int num) {
        return String.valueOf(RAND_STRING.charAt(num));
    }

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
}
