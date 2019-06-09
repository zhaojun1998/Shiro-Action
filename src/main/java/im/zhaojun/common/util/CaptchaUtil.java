package im.zhaojun.common.util;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具类
 */
public class CaptchaUtil {

    /**
     * 生成验证码
     * @param width         验证码宽度
     * @param height        验证码高度
     * @param codeCount     验证码个数
     * @param lineCount     干扰线个数
     * @param lineLenght    干扰线长度
     * @return  验证码对象
     */
    public static Captcha createCaptcha(int width, int height, int codeCount, int lineCount, int lineLenght) {
        BufferedImage image = new BufferedImage(width, height, ColorSpace.TYPE_Lab);
        Graphics g = image.getGraphics();
        Random random = new Random();

        // 取颜色区间中较淡的部分
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        g.setColor(getRandColor(160,200));
        // 干扰线
        for (int i = 0; i < lineCount; ++i) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xLength = random.nextInt(lineLenght);
            int yLength = random.nextInt(lineLenght);
            g.drawLine(x, y, x + xLength, y + yLength);
        }

        StringBuilder code = new StringBuilder();

        // 生成验证码
        for (int i = 0; i < codeCount; ++i) {
            String rand = String.valueOf(random.nextInt(10));
            code.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, (width / codeCount) * i + (width / codeCount / 2),  height / 2 + 9);
        }

        g.dispose();
        return new Captcha(code.toString(), image);
    }

    /**
     * 给定范围内获取颜色值
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 验证码对象
     */
    public static class Captcha {
        private final String code;
        private final BufferedImage image;

        public String getCode() {
            return code;
        }

        public BufferedImage getImage() {
            return image;
        }

        private Captcha(String code, BufferedImage image) {
            this.code = code;
            this.image = image;
        }
    }


}
