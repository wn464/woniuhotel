package com.project.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class CodeUtil {

    private static int codeCount = 4;// 定义图片上显示验证码的个数
   
    private static char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    
  
    public static StringBuffer generateCode() {
       
        Random random = new Random();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(10)]);
   
            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        return randomCode;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("验证码的值为："+CodeUtil.generateCode());
    }
}